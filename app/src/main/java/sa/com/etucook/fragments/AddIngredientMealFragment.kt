package sa.com.etucook.fragments

import android.os.Bundle
import android.view.*

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.add_ingredient_meal_fragment.*
import sa.com.etucook.model.IngredientMealJoin
import sa.com.etucook.recycler_adapter.AddIngredientMealRecyclerAdapter
import sa.com.etucook.view_models.AddMealIngredientViewModel
import sa.com.etucook.view_models.viewModelFactory

private const val ADD_MEAL_INGREDIENT_ID = "sa.com.etucook.add_ingredient_meal_id"

class AddIngredientMealFragment: Fragment(), AddIngredientMealRecyclerAdapter.OnItemClickListener {

    private var mealId: Long = 0L

    val args: AddIngredientMealFragmentArgs by navArgs()

    private var addIngredientMealViewAdapter = AddIngredientMealRecyclerAdapter(this)
    private lateinit var addIngredientMealVM: AddMealIngredientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mealId = savedInstanceState?.getLong(ADD_MEAL_INGREDIENT_ID) ?: args.mealId

        addIngredientMealVM = ViewModelProviders.of(this, viewModelFactory { AddMealIngredientViewModel(mealId)})
            .get(AddMealIngredientViewModel::class.java)
        addIngredientMealVM.ingredient.observe(this, Observer {
            addIngredientMealViewAdapter.submitList(it)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = sa.com.etucook.databinding.AddIngredientMealFragmentBinding.inflate(inflater)
        binding.addIngredientMealVM = addIngredientMealVM
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_ingredient_meal_recycler_view.adapter = addIngredientMealViewAdapter
    }

    override fun onItemClick(ingredientId: Long) {
        addIngredientMealVM.addIngredientMealJoin(IngredientMealJoin(ingredientId, mealId))
    }
}