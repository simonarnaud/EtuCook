package sa.com.etucook.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import sa.com.etucook.model.Ingredient
import sa.com.etucook.model.Meal

@Dao
interface MealDao {

    @Query("SELECT * from meal_table")
    fun getAllMeals(): LiveData<List<Meal>>

    @Insert(onConflict = REPLACE)
    fun insert(meal: Meal)

    @Query("DELETE from meal_table")
    fun deleteAllMeals()

    @Delete
    fun deleteMeal(meal: Meal)

    @Update(onConflict = REPLACE)
    fun updateMeal(meal: Meal)

    @Query("SELECT * FROM meal_table where meal_id == :id")
    fun getMealById(id: Long): Meal


}