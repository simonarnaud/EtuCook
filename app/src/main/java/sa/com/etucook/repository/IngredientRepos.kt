package sa.com.etucook.repository

import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.Ingredient
import sa.com.etucook.threadWorker.DataBaseThreadWorker

class IngredientRepos {

    companion object {

        private var mEtuCoockDataBase: EtuCoockDataBase? = null
        private lateinit var mDataBaseThreadWorker: DataBaseThreadWorker

        fun insertIngtredient(ingredient: Ingredient) {
            val task = Runnable { mEtuCoockDataBase?.ingredientDao()?.insert(ingredient) }
            mDataBaseThreadWorker.postTask(task)
        }

        fun deleteIngredient(ingredient: Ingredient) {
            val task = Runnable { mEtuCoockDataBase?.ingredientDao()?.deleteIngredient(ingredient) }
            mDataBaseThreadWorker.postTask(task)
        }

        fun deleteAllIngredients() {
            val task = Runnable { mEtuCoockDataBase?.ingredientDao()?.deleteAllIngredients() }
            mDataBaseThreadWorker.postTask(task)
        }

        fun updateIngredient(ingredient: Ingredient) {
            val task = Runnable { mEtuCoockDataBase?.ingredientDao()?.updateIngredient(ingredient) }
            mDataBaseThreadWorker.postTask(task)
        }

        fun getAllIngredients() {
            val task = Runnable { mEtuCoockDataBase?.ingredientDao()?.getAllIngredients() }
            mDataBaseThreadWorker.postTask(task)
        }

        fun getIngredientById(id: Long) {
            val task = Runnable { mEtuCoockDataBase?.ingredientDao()?.getIngredientById(id) }
            mDataBaseThreadWorker.postTask(task)
        }
    }
}