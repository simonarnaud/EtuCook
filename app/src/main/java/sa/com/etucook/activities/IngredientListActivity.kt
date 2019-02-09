package sa.com.etucook.activities

import android.net.Uri
import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar_md_activity_content.*
import sa.com.etucook.R
import sa.com.etucook.fragments.IngredientFragment
import sa.com.etucook.fragments.IngredientListFragment

class IngredientListActivity : SimpleFragmentActivity(), IngredientListFragment.OnSomethingMoveInListFragment {
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
            println("pas de selection")
            //startActivity(IngredientPagerActivity.getIntent(this, position))
        }
    }

   // override fun onNewIngredient() = startActivity(IngredientActivity.getIntent(this, null))
   // override fun onIngredientSave() {}

    private fun removeDisplayedFragment() {
        supportFragmentManager.findFragmentById(R.id.container_fragment_detail)?.let {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
    }
}