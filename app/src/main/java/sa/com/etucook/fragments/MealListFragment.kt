package sa.com.etucook.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.meal_list_fragment.*
import sa.com.etucook.R
import sa.com.etucook.databinding.MealListFragmentBinding
import sa.com.etucook.recycler_adapter.MealRecyclerAdapter
import sa.com.etucook.retrofit.RetrofitFactory
import sa.com.etucook.view_models.MealListViewModel
import java.lang.RuntimeException

class MealListFragment: Fragment(), MealRecyclerAdapter.OnItemClickListener {

    interface OnSomethingMoveInListFragment {
        fun onMealSelected(mealId: Long)
        fun onAddNewMeal()
    }
    private var listener: OnSomethingMoveInListFragment? = null

    private var mealViewAdapter = MealRecyclerAdapter(this)
    private lateinit var mealListVM: MealListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mealListVM = ViewModelProviders.of(this).get(MealListViewModel::class.java)
        mealListVM.meals.observe(this, Observer {
            mealViewAdapter.submitList(it)
        })
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = sa.com.etucook.databinding.MealListFragmentBinding.inflate(inflater)
        binding.mealListVM = mealListVM
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meal_recycler_view.adapter = mealViewAdapter

        meal_floating_action_button.setOnClickListener {
            addNewMeal()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_list_meal, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_meal -> {
                deleteAllMeals()
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

    private fun deleteAllMeals() {
        mealListVM.deleteAllMeals()
    }

    fun addNewMeal() {
        listener?.onAddNewMeal()
    }

    override fun onItemClick(mealId: Long) {
        listener?.onMealSelected(mealId)
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