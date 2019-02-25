package sa.com.etucook.repository

import sa.com.etucook.dao.IngredientDao
import sa.com.etucook.model.Ingredient
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

val EXECUTOR: ExecutorService = Executors.newSingleThreadExecutor()

class IngredientRepos(private val ingredientDao: IngredientDao) {

    fun insertIngredient(ingredient: Ingredient) {
        ingredient.ingredientName.capitalize()
        EXECUTOR.execute { ingredientDao.insert(ingredient) }
    }

    fun deleteIngredient(id: Long?) = EXECUTOR.execute { ingredientDao.deleteIngredient(id) }

    fun deleteAllIngredients() = EXECUTOR.execute { ingredientDao.deleteAllIngredients() }

    fun updateIngredient(ingredient: Ingredient) {
        ingredient.ingredientName.capitalize()
        EXECUTOR.execute { ingredientDao.updateIngredient(ingredient) }
    }

    fun getAllIngredients() =  ingredientDao.getAllIngredients()

    fun getIngredientById(id: Long?) = ingredientDao.getIngredientById(id)
}