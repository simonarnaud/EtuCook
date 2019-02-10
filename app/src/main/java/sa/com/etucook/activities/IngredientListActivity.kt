package sa.com.etucook.activities

import android.net.Uri
import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar_md_activity_content.*
import sa.com.etucook.R
import sa.com.etucook.fragments.IngredientFragment
import sa.com.etucook.fragments.IngredientListFragment

class IngredientListActivity : SimpleFragmentActivity(), IngredientListFragment.OnSomethingMoveInListFragment, IngredientFragment.OnInteractionListener {
    private var isTwoPane: Boolean = false

    override fun createFragment() = IngredientListFragment()
    override fun getLayoutResId() = R.layout.toolbar_md_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isTwoPane = container_fragment_detail != null

        if(!isTwoPane) {
            removeDisplayedFragment()
        }
    }

    override fun onIngredientSelected(ingredientUri: Uri, position: Int) {
        if (isTwoPane) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_fragment_detail, IngredientFragment.newInstance(ingredientUri))
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .addToBackStack("ingredient_back")
                .replace(R.id.container_fragment, IngredientFragment.newInstance(ingredientUri), "ingredient_back")
                .commit()
        }
    }

   // override fun onIngredientSave() {}

    private fun removeDisplayedFragment() {
        supportFragmentManager.findFragmentById(R.id.container_fragment_detail)?.let {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
    }

    override fun onIngredientSaved() { }

    override fun onIngredientDeleted() {
        if(isTwoPane) {
            removeDisplayedFragment()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}