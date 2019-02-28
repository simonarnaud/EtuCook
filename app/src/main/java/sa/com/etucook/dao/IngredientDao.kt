package sa.com.etucook.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import sa.com.etucook.model.Ingredient

@Dao
interface IngredientDao {

    @Query("SELECT * from ingredient_table")
    fun getAllIngredients(): LiveData<List<Ingredient>>

    @Insert(onConflict = REPLACE)
    fun insert(ingredient: Ingredient)

    @Query("Delete from ingredient_table")
    fun deleteAllIngredients()

    @Query("Delete from ingredient_table where id == :id")
    fun deleteIngredient(id: Long?)

    @Update(onConflict = REPLACE)
    fun updateIngredient(ingredient: Ingredient)

    @Query("SELECT * FROM ingredient_table where id == :id")
    fun getIngredientById(id: Long?): LiveData<Ingredient>

}