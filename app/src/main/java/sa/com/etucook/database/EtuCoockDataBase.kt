package sa.com.etucook.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sa.com.etucook.dao.IngredientDao
import sa.com.etucook.dao.IngredientMealJoinDao
import sa.com.etucook.dao.MealDao
import sa.com.etucook.model.Ingredient
import sa.com.etucook.model.IngredientMealJoin
import sa.com.etucook.model.Meal

@Database(entities = [Ingredient::class, Meal::class, IngredientMealJoin::class], version = 1)
abstract class EtuCoockDataBase: RoomDatabase() {

    abstract fun ingredientDao(): IngredientDao
    abstract fun mealDao(): MealDao
    abstract fun ingredientMealJoinDao(): IngredientMealJoinDao

    companion object {
        private var INSTANCE: EtuCoockDataBase? = null
        private var APPLICATION: Application? = null

        fun getInstance(): EtuCoockDataBase {
            if(INSTANCE == null) {
                synchronized(EtuCoockDataBase::class) {
                    INSTANCE = Room.databaseBuilder(APPLICATION!!.applicationContext, EtuCoockDataBase::class.java, "etu_coock.db").build()
                }
            }
            return INSTANCE as EtuCoockDataBase
        }

        @Synchronized fun setApplication(app: Application) {
            if(APPLICATION == null) {
                APPLICATION = app
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}