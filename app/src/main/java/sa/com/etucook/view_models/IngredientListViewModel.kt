package sa.com.etucook.view_models

import androidx.lifecycle.ViewModel
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.Ingredient
import sa.com.etucook.repository.IngredientRepos

class IngredientListViewModel : ViewModel() {

    private val ingredientRepos = IngredientRepos(EtuCoockDataBase.getInstance().ingredientDao())
    val ingredients = ingredientRepos.getAllIngredients()

    fun addIngredient(ingredient: Ingredient) = ingredientRepos.insertIngredient(ingredient)
    fun deleteAllIngredients() = ingredientRepos.deleteAllIngredients()
    fun deleteIngredient(id: Long) = ingredientRepos.deleteIngredient(id)
}
