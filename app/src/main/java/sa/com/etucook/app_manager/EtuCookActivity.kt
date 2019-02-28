package sa.com.etucook.app_manager

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.map_fragment.*
import kotlinx.android.synthetic.main.toolbar_md_activity.*
import sa.com.etucook.R
import sa.com.etucook.fragments.*

class EtuCookActivity : AppCompatActivity(), IngredientListFragment.OnSomethingMoveInListFragment, IngredientFragment.OnInteractionListener, MealListFragment.OnSomethingMoveInListFragment, MealFragment.OnInteractionListener {

    protected lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.toolbar_md_activity)

       /* isTwoPane = container_fragment_detail != null

        if(!isTwoPane) {
            removeDisplayedFragment()
        }*/

        navController = Navigation.findNavController(this, R.id.nav_fragment)

        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, AppBarConfiguration(navController.graph))
    }

   // private var isTwoPane: Boolean = false

    //override fun createFragment() = IngredientListFragment()
    //override fun getLayoutResId() = R.layout.toolbar_md_activity

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       isTwoPane = container_fragment_detail != null

        if(!isTwoPane) {
            removeDisplayedFragment()
        }
    }*/

    override fun onIngredientSelected(ingredientId: Long) {
       /* if (isTwoPane) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_fragment_detail, IngredientFragment.newInstance(ingredientUri))
                .commit()
        } else {*/
            val action = IngredientListFragmentDirections.actionIngredientDetail(ingredientId)
            navController.navigate(action)
       // }

    }

    override fun onMealSelected(mealId: Long) {
        val action = MealListFragmentDirections.actionMealDetail(mealId)
        navController.navigate(action)
    }

    override fun onShowIngredients() {
        val action = MealFragmentDirections.actionListIngredient()
        navController.navigate(action)
    }

    override fun onMapRequired() {
        val action = MealListFragmentDirections.actionMap()
        navController.navigate(action)
    }

    override fun onAddNewIngredient() {
        navController.navigate(R.id.action_ingredient_detail)
    }

    override fun onAddNewMeal() {
        navController.navigate(R.id.action_meal_detail)
    }

    /*private fun removeDisplayedFragment() {
        supportFragmentManager.findFragmentById(R.id.container_fragment_detail)?.let {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
    }*/

    override fun onIngredientSaved() {
        hideKeyBoard()
        navController.popBackStack()
    }

    override fun onMealSaved() {
        hideKeyBoard()
        navController.popBackStack()
    }

    override fun onIngredientDeleted() {
       /* if(isTwoPane) {
            removeDisplayedFragment()
        } else {*/
            hideKeyBoard()
            navController.popBackStack()
       // }
    }

    override fun onMealDeleted() {
        hideKeyBoard()
        navController.popBackStack()
    }

    private fun hideKeyBoard() {
        val view = this.currentFocus
        if(view != null) {
            val keyBoard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            keyBoard.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}