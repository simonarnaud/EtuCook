package sa.com.etucook.view_models

import androidx.lifecycle.ViewModel
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.IngredientMealJoin
import sa.com.etucook.repository.IngredientMealJoinRepos
import sa.com.etucook.repository.IngredientRepos

class AddMealIngredientViewModel(private val mealId: Long) : ViewModel(){

    private val ingredientMealJoinRepos = IngredientMealJoinRepos(EtuCoockDataBase.getInstance().ingredientMealJoinDao())
    private val ingredientRepos = IngredientRepos(EtuCoockDataBase.getInstance().ingredientDao())
    val ingredientMealJoin = ingredientMealJoinRepos.getIngredientsFromMeal(mealId)
    val ingredient = ingredientRepos.getAllIngredients()

    fun addIngredientMealJoin(ingredientMealJoin: IngredientMealJoin) = ingredientMealJoinRepos.insertIngtredientMealJoin(ingredientMealJoin)
}
