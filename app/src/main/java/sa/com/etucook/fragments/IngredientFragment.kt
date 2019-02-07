package sa.com.etucook.fragments

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.ingredient_fragment.*
import sa.com.etucook.R
import sa.com.etucook.database.EtuCoockDataBase

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ingredientUri = savedInstanceState?.getParcelable(EXTRA_INGREDIENT_URI)?: arguments?.getParcelable(
            EXTRA_INGREDIENT_URI)

        val ingredientId = ingredientUri?.getQueryParameter("ingredient_id")
        println("Ingredient id : " + ingredientId)
        //appel vm

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EtuCoockDataBase.destroyInstance()
    }

    //a virer plus tard
    private fun initContent() {
        activity.let {
            ingredient_name.text = "test"
            ingredient_cost.text = "test"
        }
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