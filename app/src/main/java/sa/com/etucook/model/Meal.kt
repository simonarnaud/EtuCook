package sa.com.etucook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "meal_table")
data class Meal (

    @ColumnInfo(name = "meal_id")
    @PrimaryKey(autoGenerate = true)
    var id : Long?,

    @ColumnInfo(name = "meal_name")
    var mealName : String,

    @ColumnInfo(name = "meal_cost")
    var mealCost : Float
) {
    @Ignore constructor():this(null,"",0F)
}