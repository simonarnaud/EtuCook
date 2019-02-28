package sa.com.etucook.view_models

import androidx.lifecycle.ViewModel
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.IngredientMealJoin
import sa.com.etucook.repository.IngredientMealJoinRepos

class MealIngredientViewModel(private val mealId: Long) : ViewModel(){

    private val ingredientMealJoinRepos = IngredientMealJoinRepos(EtuCoockDataBase.getInstance().ingredientMealJoinDao())
    val ingredientMealJoin = ingredientMealJoinRepos.getIngredientsFromMeal(mealId)
}