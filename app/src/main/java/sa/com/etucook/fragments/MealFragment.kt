package sa.com.etucook.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.meal_fragment.*
import sa.com.etucook.R
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.databinding.MealFragmentBinding
import sa.com.etucook.repository.MealRepos
import sa.com.etucook.view_models.MealViewModel
import sa.com.etucook.view_models.viewModelFactory
import java.lang.RuntimeException

private const val MEAL_ID = "sa.com.etucook.meal_id"

class MealFragment : Fragment() {

    private lateinit var mealVM: MealViewModel
    private var mealId: Long = 0L

    val args: MealFragmentArgs by navArgs()

    interface OnInteractionListener {
        fun onMealSaved()
        fun onMealDeleted()
        fun onShowIngredients()
    }

    private var listener: OnInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mealId = savedInstanceState?.getLong(MEAL_ID) ?: args.mealId

        if(mealId == 0L) activity?.setTitle(getString(sa.com.etucook.R.string.create_new_meal))

        mealVM = ViewModelProviders.of(this, viewModelFactory { MealViewModel(MealRepos(EtuCoockDataBase.getInstance().mealDao()), mealId) })
            .get(MealViewModel::class.java)

        setHasOptionsMenu(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(MEAL_ID, mealId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = sa.com.etucook.databinding.MealFragmentBinding.inflate(inflater)
        binding.mealVM = mealVM
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_show_ingredients.setOnClickListener {
            showIngredients()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if(mealId == 0L) {
            menu.findItem(R.id.action_delete)?.isVisible = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_single_object, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_save -> {
                saveMeal()
                true
            }
            R.id.action_delete -> {
                deleteMeal()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MealFragment.OnInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " You must implement OnInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun saveMeal() {
        if(mealVM.saveMeal() == false) {
            AlertDialog.Builder(activity).setTitle(getString(sa.com.etucook.R.string.error_occured))
                .setMessage(getString(sa.com.etucook.R.string.meal_error_create))
                .setNeutralButton(android.R.string.ok, null)
                .show()
            return
        }

        listener?.onMealSaved()
    }

    private fun deleteMeal() {
        mealVM.deleteMeal()
        listener?.onMealDeleted()
    }

    private fun showIngredients() {
        listener?.onShowIngredients()
    }
}