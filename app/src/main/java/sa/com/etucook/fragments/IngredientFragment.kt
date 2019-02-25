package sa.com.etucook.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import sa.com.etucook.R
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.databinding.IngredientFragmentBinding
import sa.com.etucook.repository.IngredientRepos
import sa.com.etucook.view_models.IngredientViewModel
import sa.com.etucook.view_models.viewModelFactory
import java.lang.RuntimeException

private const val INGREDIENT_ID = "sa.com.etucook.ingredient_id"

class IngredientFragment : Fragment() {

    private lateinit var ingredientVM: IngredientViewModel
    private var ingredientId: Long = 0L

    val args: IngredientFragmentArgs by navArgs()

    interface OnInteractionListener {
        fun onIngredientSaved()
        fun onIngredientDeleted()
    }

    private var listener: OnInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ingredientId = savedInstanceState?.getLong(INGREDIENT_ID) ?: args.ingredientId

        if(ingredientId == 0L) activity?.setTitle(getString(sa.com.etucook.R.string.create_new_ingredient))

        ingredientVM = ViewModelProviders.of(this, viewModelFactory { IngredientViewModel(IngredientRepos(EtuCoockDataBase.getInstance().ingredientDao()), ingredientId) })
            .get(IngredientViewModel::class.java)

        setHasOptionsMenu(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(INGREDIENT_ID, ingredientId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = IngredientFragmentBinding.inflate(inflater)
        binding.ingredientVM = ingredientVM
        //binding.lifecycleOwner = this
        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if(ingredientId == 0L) {
            menu.findItem(R.id.action_delete)?.isVisible = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_ingredient, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                saveIngredient()
                true
            }
            R.id.action_delete -> {
                deleteIngredient()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is IngredientFragment.OnInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " You must implement OnInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun saveIngredient() {
        if(ingredientVM.saveIngredient() == false) {
            AlertDialog.Builder(activity).setTitle(getString(sa.com.etucook.R.string.error_occured))
                .setMessage(getString(sa.com.etucook.R.string.ingredient_error_create))
                .setNeutralButton(android.R.string.ok, null)
                .show()
            return
        }

        listener?.onIngredientSaved()
    }

    private fun deleteIngredient() {
       ingredientVM.deleteIngredient()
        listener?.onIngredientDeleted()
    }
}