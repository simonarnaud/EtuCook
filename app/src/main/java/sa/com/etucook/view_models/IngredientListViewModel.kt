package sa.com.etucook.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.Ingredient
import sa.com.etucook.repository.IngredientRepos
import sa.com.etucook.threadWorker.DataBaseThreadWorker

class IngredientListViewModel(application: Application) : AndroidViewModel(application) {

    private val ingredientRepos = IngredientRepos(application)
    var ingredients: LiveData<List<Ingredient>>

    init {
        ingredients = ingredientRepos.ingredients
    }

    fun addIngredient(ingredient: Ingredient) {
        ingredientRepos.insertIngredient(ingredient)
    }

    fun deleteAllIngredients() {
        ingredientRepos.deleteAllIngredients()
    }
}
