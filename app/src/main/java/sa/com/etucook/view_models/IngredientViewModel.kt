package sa.com.etucook.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.Ingredient
import sa.com.etucook.repository.IngredientRepos
import sa.com.etucook.threadWorker.DataBaseThreadWorker

class IngredientViewModel(application: Application, ingredientId: Long) : AndroidViewModel(application) {

    private val ingredientRepos = IngredientRepos(application)
    var ingredient: LiveData<Ingredient>

    init {
        ingredient = ingredientRepos.getIngredientById(ingredientId)
    }
}
