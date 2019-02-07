package sa.com.etucook.fragments

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.ingredient_list_fragment.*
import sa.com.etucook.R
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.Ingredient
import sa.com.etucook.recycler_adapter.IngredientRecyclerAdapter
import sa.com.etucook.view_models.IngredientListViewModel

class IngredientListFragment: Fragment(), IngredientRecyclerAdapter.OnItemClickListener {

    interface OnSomethingMoveInListFragment {
        fun onIngredientSelected(ingredientUri: Uri, position: Int)
    }
    private val listener: OnSomethingMoveInListFragment? = null

    //pas sur
    private var db: EtuCoockDataBase? = null

    private var ingredientRecyclerView: RecyclerView? = null
    private var recyclerViewAdapter = IngredientRecyclerAdapter(arrayListOf(), this)

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setHasOptionsMenu(true)
   }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        db = EtuCoockDataBase.getInstance()

        val rootView = inflater.inflate(R.layout.ingredient_list_fragment, container, false)
        ingredientRecyclerView = rootView.findViewById(R.id.ingredient_recycler_view) as RecyclerView
        ingredientRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        ingredientRecyclerView!!.adapter = recyclerViewAdapter
        return rootView
    }

    private lateinit var viewModel: IngredientListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(IngredientListViewModel::class.java)
        viewModel.addIngredient(Ingredient(null, "riz", 10F))
        viewModel.getListIngredients().observe(this, Observer { ingredients -> recyclerViewAdapter.addIngredients(ingredients!!) })

        //floating action button
        ingredient_floating_action_button.setOnClickListener {

            //var intent = Intent(applicationContext, ContactDetailsActivity::class.java)
            //startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EtuCoockDataBase.destroyInstance()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_ingredient, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_ingredient -> {
                deleteAllIngredients()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //pas sur
    private fun deleteAllIngredients() {
        db!!.ingredientDao().deleteAllIngredients()
    }

    override fun onItemClick(ingredient: Ingredient, position: Int) {
        System.out.println("Ingredient : " + ingredient.ingredientName)
        var uri = Uri.parse(ingredient.toString())
        uri = uri.buildUpon()
            .appendQueryParameter("ingredient_id", ingredient.id.toString())
            .build()
        listener?.onIngredientSelected(uri, position)
    }
}