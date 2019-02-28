package sa.com.etucook.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.ingredient_meal_fragment.*
import sa.com.etucook.R
import sa.com.etucook.recycler_adapter.IngredientMealRecyclerAdapter
import sa.com.etucook.view_models.MealIngredientViewModel
import sa.com.etucook.view_models.viewModelFactory
import java.lang.RuntimeException

private const val MEAL_INGREDIENT_ID = "sa.com.etucook.ingredient_meal_id"

class IngredientMealFragment: Fragment(), IngredientMealRecyclerAdapter.OnItemClickListener {

    private var mealId: Long = 0L

    val args: IngredientMealFragmentArgs by navArgs()

    interface OnSomethingMoveInListFragment {
        fun onAddNewIngredientMeal()
    }
    private var listener: OnSomethingMoveInListFragment? = null

    private var ingredientMealViewAdapter = IngredientMealRecyclerAdapter(this)
    private lateinit var ingredientMealVM: MealIngredientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mealId = savedInstanceState?.getLong(MEAL_INGREDIENT_ID) ?: args.mealId

        ingredientMealVM = ViewModelProviders.of(this, viewModelFactory { MealIngredientViewModel(mealId)})
            .get(MealIngredientViewModel::class.java)
        ingredientMealVM.ingredientMealJoin.observe(this, Observer {
            ingredientMealViewAdapter.submitList(it)
        })
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = sa.com.etucook.databinding.IngredientMealFragmentBinding.inflate(inflater)
        binding.ingredientMealVM = ingredientMealVM
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingredient_meal_recycler_view.adapter = ingredientMealViewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_ingredient_meal, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_ingredient_to_meal -> {
                addIngredientToMeal()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
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

    fun addIngredientToMeal() {
        listener?.onAddNewIngredientMeal()
    }

    override fun onItemClick(ingredientName: String) {
        Toast.makeText(context, ingredientName, Toast.LENGTH_SHORT).show()
    }
}