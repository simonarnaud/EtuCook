package sa.com.etucook.view_models

import androidx.lifecycle.ViewModel
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.Meal
import sa.com.etucook.repository.MealRepos

class MealListViewModel : ViewModel() {

    private val mealRepos = MealRepos(EtuCoockDataBase.getInstance().mealDao())
    val meals = mealRepos.getAllMeals()

    fun addMeal(meal: Meal) = mealRepos.insertMeal(meal)
    fun deleteAllMeals() = mealRepos.deleteAllMeals()
    fun deletemeal(meal: Meal) = mealRepos.deleteMeal(meal)
}
