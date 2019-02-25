package sa.com.etucook.repository

import sa.com.etucook.dao.IngredientMealJoinDao
import sa.com.etucook.model.IngredientMealJoin
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

val EXECUTOR_MEAL_JOIN_INGREDIENT: ExecutorService = Executors.newSingleThreadExecutor()

class IngredientMealJoinRepos(private val ingredientMealJoinDao: IngredientMealJoinDao) {

    fun insertIngtredientMealJoin(ingredientMealJoin: IngredientMealJoin) = EXECUTOR_MEAL_JOIN_INGREDIENT.execute { ingredientMealJoinDao.insert(ingredientMealJoin) }
    fun getMealsFromIngredient(ingredientId: Long) = ingredientMealJoinDao.getMealsFromIngredient(ingredientId)
    fun getIngredientsFromMeal(mealId: Long) = ingredientMealJoinDao.getIngredientsFromMeal(mealId)
}