package sa.com.etucook.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import sa.com.etucook.model.Ingredient
import sa.com.etucook.model.IngredientMealJoin
import sa.com.etucook.model.Meal

@Dao
interface IngredientMealJoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ingredientMealJoin: IngredientMealJoin)

    @Query("SELECT * from meal_table inner join ingredient_meal " +
            "on id = mealId " +
            "where ingredientId = :ingredientId")
    fun getMealsFromIngredient(ingredientId: Long): LiveData<List<Meal>>

    @Query("SELECT * from ingredient_table inner join ingredient_meal " +
            "on id = ingredientId " +
            "where mealId = :mealId")
    fun getIngredientsFromMeal(mealId: Long): LiveData<List<Ingredient>>
}