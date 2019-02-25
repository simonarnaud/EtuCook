package sa.com.etucook.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sa.com.etucook.model.Ingredient
import sa.com.etucook.repository.IngredientRepos

class IngredientViewModel(private val ingredientRepos: IngredientRepos, ingredientId: Long) : ViewModel() {
    val ingredient = if (ingredientId == 0L) MutableLiveData<Ingredient>().apply { value = Ingredient() }
    else ingredientRepos.getIngredientById(ingredientId)

    fun saveIngredient() = ingredient.value?.let {
        if(it.ingredientName.isBlank() || it.ingredientCost.isNaN()) {
            false
        } else {
            if(it.id == 0L) ingredientRepos.insertIngredient(it) else ingredientRepos.updateIngredient(it)
            true
        }
    }

    fun deleteIngredient() = ingredient.value?.let { ingredientRepos.deleteIngredient(it.id) }
}
