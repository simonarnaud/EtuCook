package sa.com.etucook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table")
data class Ingredient (

    @ColumnInfo(name = "ingredient_id")
    @PrimaryKey(autoGenerate = true)
    var id : Long?,

    @ColumnInfo(name = "ingredient_name")
    var ingredientName : String,

    @ColumnInfo(name = "ingredient_cost")
    var ingredientCost : Float
){
   @Ignore constructor():this(null,"", 0F)
}