package sa.com.etucook.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sa.com.etucook.model.Meal
import sa.com.etucook.repository.MealRepos

class MealViewModel(private val mealRepos: MealRepos, mealId: Long) : ViewModel() {
    val meal = if (mealId == 0L) MutableLiveData<Meal>().apply { value = Meal() }
    else mealRepos.getMealById(mealId)

    fun saveMeal() = meal.value?.let {
        if(it.mealName.isBlank() || it.mealCost == 0F) {
            false
        } else {
            if(it.id == 0L) mealRepos.insertMeal(it) else mealRepos.updateMeals(it)
            true
        }
    }
    fun deleteMeal() = meal.value?.let { mealRepos.deleteMeal(it) }
}
