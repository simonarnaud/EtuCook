package sa.com.etucook.dao

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
            "on meal_id = mealId " +
            "where ingredientId = :ingredientId")
    fun getMealsFromIngredient(ingredientId: Long): List<Meal>

    @Query("SELECT * from ingredient_table inner join ingredient_meal " +
            "on ingredient_id = ingredientId " +
            "where mealId = :mealId")
    fun getIngredientsFromMeal(mealId: Long): List<Ingredient>
}