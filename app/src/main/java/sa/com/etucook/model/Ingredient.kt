package sa.com.etucook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table")
data class Ingredient (

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,

    @ColumnInfo(name = "ingredient_name")
    var ingredientName : String,

    @ColumnInfo(name = "ingredient_cost")
    var ingredientCost : Float
){
   @Ignore constructor():this(0L,"", 0F)
}