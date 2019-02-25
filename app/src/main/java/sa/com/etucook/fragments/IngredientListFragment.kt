package sa.com.etucook.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.ingredient_list_fragment.*
import sa.com.etucook.R
import sa.com.etucook.databinding.IngredientListFragmentBinding
import sa.com.etucook.recycler_adapter.IngredientRecyclerAdapter
import sa.com.etucook.retrofit.RetrofitFactory
import sa.com.etucook.retrofit.RetrofitService
import sa.com.etucook.view_models.IngredientListViewModel
import java.lang.RuntimeException

class IngredientListFragment: Fragment(), IngredientRecyclerAdapter.OnItemClickListener {

    interface OnSomethingMoveInListFragment {
        fun onIngredientSelected(ingredientId: Long)
        fun onAddNewIngredient()
    }
    private var listener: OnSomethingMoveInListFragment? = null

    private var ingredientViewAdapter = IngredientRecyclerAdapter(this)
    private lateinit var ingredientListVM: IngredientListViewModel

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)

       ingredientListVM = ViewModelProviders.of(this).get(IngredientListViewModel::class.java)
       ingredientListVM.ingredients.observe(this, Observer {
           ingredientViewAdapter.submitList(it)
       })

       setHasOptionsMenu(true)
   }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = IngredientListFragmentBinding.inflate(inflater)
        binding.ingredientListVM = ingredientListVM
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ingredient_recycler_view.adapter = ingredientViewAdapter

        ingredient_floating_action_button.setOnClickListener {
            addNewIngredient()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_list_ingredient, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_ingredient -> {
                deleteAllIngredients()
                true
            }
            R.id.get_marketplace -> {
               // RetrofitFactory.getMarkets(49.151676F, -0.423753F,49.194477F, -0.344102F)
                RetrofitFactory.getMarkets()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllIngredients() {
        ingredientListVM.deleteAllIngredients()
    }

    fun addNewIngredient() {
        listener?.onAddNewIngredient()
    }

    override fun onItemClick(ingredientId: Long) {
        listener?.onIngredientSelected(ingredientId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnSomethingMoveInListFragment) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " You must implement OnSomethingMoveInListFragment")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}