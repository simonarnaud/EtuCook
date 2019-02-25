package sa.com.etucook.activities

import sa.com.etucook.fragments.IngredientFragment
import sa.com.etucook.fragments.IngredientListFragment
import sa.com.etucook.fragments.IngredientListFragmentDirections

class IngredientListActivity : SimpleFragmentActivity(), IngredientListFragment.OnSomethingMoveInListFragment, IngredientFragment.OnInteractionListener {
   // private var isTwoPane: Boolean = false

  //  override fun createFragment() = IngredientListFragment()
  //  override fun getLayoutResId() = R.layout.toolbar_md_activity

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       isTwoPane = container_fragment_detail != null

        if(!isTwoPane) {
            removeDisplayedFragment()
        }
    }*/

    override fun onIngredientSelected(ingredientId: Long) {
        /*if (isTwoPane) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_fragment_detail, IngredientFragment.newInstance(ingredientUri))
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .addToBackStack("ingredient_back")
                .replace(R.id.container_fragment, IngredientFragment.newInstance(ingredientUri), "ingredient_back")
                .commit()
        }*/

        val action = IngredientListFragmentDirections.actionIngredientDetail(ingredientId)
        navController.navigate(action)
    }

    override fun onAddNewIngredient() {
        navController.navigate(R.id.action_ingredient_detail)
    }

    /*private fun removeDisplayedFragment() {
        supportFragmentManager.findFragmentById(R.id.container_fragment_detail)?.let {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
    }*/

    override fun onIngredientSaved() {
        navController.popBackStack()
    }

    override fun onIngredientDeleted() {
        /*if(isTwoPane) {
            removeDisplayedFragment()
        } else {
            supportFragmentManager.popBackStack()
        }*/

        navController.popBackStack()
    }
}