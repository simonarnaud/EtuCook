package sa.com.etucook.repository

import sa.com.etucook.dao.MealDao
import sa.com.etucook.model.Ingredient
import sa.com.etucook.model.Meal
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

val EXECUTOR_MEAL: ExecutorService = Executors.newSingleThreadExecutor()

class MealRepos(private val mealDao: MealDao) {
        fun insertMeal(meal: Meal) {
                meal.mealName = meal.mealName.capitalize()
                EXECUTOR_MEAL.execute { mealDao.insert(meal) }
        }

        fun deleteMeal(meal: Meal) = EXECUTOR_MEAL.execute { mealDao.deleteMeal(meal) }

        fun deleteAllMeals() = EXECUTOR_MEAL.execute { mealDao.deleteAllMeals() }

        fun updateMeals(meal: Meal) {
                meal.mealName = meal.mealName.capitalize()
                EXECUTOR_MEAL.execute { mealDao.updateMeal(meal) }
        }

        fun getAllMeals() = mealDao.getAllMeals()

        fun getMealById(id: Long?) = mealDao.getMealById(id)
}