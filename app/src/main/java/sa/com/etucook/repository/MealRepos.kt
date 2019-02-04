package sa.com.etucook.repository

import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.Meal
import sa.com.etucook.threadWorker.DataBaseThreadWorker

class MealRepos {

    companion object {

        private var mEtuCoockDataBase: EtuCoockDataBase? = null
        private lateinit var mDataBaseThreadWorker: DataBaseThreadWorker

        fun insertMealInDataBase(meal: Meal) {
            val task = Runnable { mEtuCoockDataBase?.mealDao()?.insert(meal) }
            mDataBaseThreadWorker.postTask(task)
        }

        fun getAllMeals() {
            val task = Runnable { mEtuCoockDataBase?.mealDao()?.getAllMeals() }
            mDataBaseThreadWorker.postTask(task)
        }

        fun deleteAllMeals() {
            val task = Runnable { mEtuCoockDataBase?.mealDao()?.deleteAllMeals() }
            mDataBaseThreadWorker.postTask(task)
        }

        fun deleteMeal(meal: Meal) {
            val task = Runnable { mEtuCoockDataBase?.mealDao()?.deleteMeal(meal) }
            mDataBaseThreadWorker.postTask(task)
        }

        fun updateMeal(meal: Meal) {
            val task = Runnable { mEtuCoockDataBase?.mealDao()?.updateMeal(meal) }
            mDataBaseThreadWorker.postTask(task)
        }

        fun getMealById(id: Long) {
            val task = Runnable { MealRepos.mEtuCoockDataBase?.mealDao()?.getMealById(id) }
            MealRepos.mDataBaseThreadWorker.postTask(task)
        }
    }
}