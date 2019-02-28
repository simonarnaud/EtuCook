package sa.com.etucook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "meal_table")
data class Meal (

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,

    @ColumnInfo(name = "meal_name")
    var mealName : String,

    @ColumnInfo(name = "meal_cost")
    var mealCost : Float
) {
    @Ignore constructor():this(0L,"",0F)
}