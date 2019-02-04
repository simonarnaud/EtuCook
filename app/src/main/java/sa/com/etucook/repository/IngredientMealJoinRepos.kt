package sa.com.etucook.repository

import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.IngredientMealJoin
import sa.com.etucook.threadWorker.DataBaseThreadWorker

class IngredientMealJoinRepos {

    companion object {

        private var mIngredientMealJoinDataBase: EtuCoockDataBase? = null
        private lateinit var mDataBaseThreadWorker: DataBaseThreadWorker

        fun insertIngtredientMealJoin(ingredientMealJoin: IngredientMealJoin) {
            val task = Runnable { IngredientMealJoinRepos.mIngredientMealJoinDataBase?.ingredientMealJoinDao()?.insert(ingredientMealJoin) }
            IngredientMealJoinRepos.mDataBaseThreadWorker.postTask(task)
        }

        fun getMealsFromIngredient(ingredientId: Long) {
            val task = Runnable { IngredientMealJoinRepos.mIngredientMealJoinDataBase?.ingredientMealJoinDao()?.getMealsFromIngredient(ingredientId)}
            IngredientMealJoinRepos.mDataBaseThreadWorker.postTask(task)
        }

        fun getIngredientsFromMeal(mealId: Long) {
            val task = Runnable { IngredientMealJoinRepos.mIngredientMealJoinDataBase?.ingredientMealJoinDao()?.getIngredientsFromMeal(mealId)}
            IngredientMealJoinRepos.mDataBaseThreadWorker.postTask(task)
        }
    }
}