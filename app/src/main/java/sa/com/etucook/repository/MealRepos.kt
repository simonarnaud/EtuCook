package sa.com.etucook.repository

import sa.com.etucook.dao.MealDao
import sa.com.etucook.model.Meal
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

val EXECUTOR_MEAL: ExecutorService = Executors.newSingleThreadExecutor()

class MealRepos(private val mealDao: MealDao) {

        fun insertMealInDataBase(meal: Meal) = EXECUTOR_MEAL.execute{ mealDao.insert(meal) }
        fun getAllMeals() = mealDao.getAllMeals()
        fun deleteAllMeals() = EXECUTOR_MEAL.execute { mealDao.deleteAllMeals() }
        fun deleteMeal(meal: Meal) = EXECUTOR_MEAL.execute { mealDao.deleteMeal(meal) }
        fun updateMeal(meal: Meal) = EXECUTOR_MEAL.execute { mealDao.updateMeal(meal) }
        fun getMealById(id: Long) = mealDao.getMealById(id)
}