package sa.com.etucook.fragments

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.ingredient_fragment.*
import sa.com.etucook.R
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.view_models.IngredientViewModel
import sa.com.etucook.view_models.viewModelFactory

class IngredientFragment : Fragment() {

    companion object {
        private const val EXTRA_INGREDIENT_URI = "sa.com.etucook.ingredient_uri"

        fun newInstance(ingredientUri: Uri?): IngredientFragment = IngredientFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_INGREDIENT_URI, ingredientUri)
            }
        }
    }

    private var ingredientUri: Uri? = null
    private var ingredientId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ingredientUri = savedInstanceState?.getParcelable(EXTRA_INGREDIENT_URI)?: arguments?.getParcelable(
            EXTRA_INGREDIENT_URI)

        ingredientId = ingredientUri?.getQueryParameter("ingredient_id")?.toLong()

        /*ingredientUri?.let {
            loaderManager.initLoader(0, null, this)
        }?: activity?.setTitle(getString(R.string.add_ingredient))*/
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(EXTRA_INGREDIENT_URI, ingredientUri)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.ingredient_fragment, container, false)
    }

    private lateinit var viewModel: IngredientViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         viewModel = ViewModelProviders.of(this, viewModelFactory { IngredientViewModel(activity!!.application,ingredientId)}).get(IngredientViewModel::class.java)

        viewModel.ingredient.observe(this, Observer {
            if(it != null) {
                ingredient_name.setText(it.ingredientName)
                ingredient_cost.setText(it.id.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EtuCoockDataBase.destroyInstance()
    }

   /* override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if(ingredientUri == null) {
            menu?.findItem(R.id.action_delete)?.isVisible = false
        }
    }*/

   /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_ingredient, menu)
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
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
    }*/
}