package sa.com.etucook.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE


@Entity(tableName = "ingredient_meal",
    primaryKeys = ["ingredientId", "mealId"],
    foreignKeys = [ForeignKey(entity = Ingredient::class,
                              parentColumns = ["ingredient_id"],
                              childColumns = ["ingredientId"],
                              onDelete = CASCADE),
                   ForeignKey(entity = Meal::class,
                              parentColumns = ["meal_id"],
                              childColumns = ["mealId"],
                              onDelete = CASCADE)]
)
data class IngredientMealJoin(val ingredientId: Long, val mealId: Long) {
}