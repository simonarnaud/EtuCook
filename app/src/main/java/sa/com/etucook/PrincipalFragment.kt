package sa.com.etucook

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.Ingredient
import sa.com.etucook.recyclerAdapter.IngredientRecyclerAdapter

class PrincipalFragment : Fragment(), IngredientRecyclerAdapter.OnItemClickListener {

    //pas sur
    private var db: EtuCoockDataBase? = null

    private var ingredientRecyclerView: RecyclerView? = null
    private var recyclerViewAdapter = IngredientRecyclerAdapter(arrayListOf(), this)

    companion object {
        fun newInstance() = PrincipalFragment()
    }

    private lateinit var viewModel: PrincipalViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        //pas sur
        db = EtuCoockDataBase.getInstance()

        val rootView = inflater.inflate(R.layout.principal_fragment, container, false)
        ingredientRecyclerView = rootView.findViewById(R.id.ingredient_recycler_view) as RecyclerView
        ingredientRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        ingredientRecyclerView!!.adapter = recyclerViewAdapter
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PrincipalViewModel::class.java)

        viewModel.getListIngredients().observe(this, Observer { ingredients -> recyclerViewAdapter.addIngredients(ingredients!!) })

        //floating action button
        /*ingredient_floating_action_button.setOnClickListener {
            var intent = Intent(applicationContext, ContactDetailsActivity::class.java)
            startActivity(intent)
        }*/

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_ingredient, menu)
    }

    override fun onItemClick(ingredient: Ingredient) {
        //changer activity
        /* var intent = Intent(applicationContext, ChangerActivity::class.java)
         intent.putExtra("idIngredient", ingredient.id)
         startActivity(intent)*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all_ingredient -> {
                //pas sur
                deleteAllIngredients()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //pas sur
    private fun deleteAllIngredients() {
        db!!.ingredientDao().deleteAllIngredients()
    }
}
