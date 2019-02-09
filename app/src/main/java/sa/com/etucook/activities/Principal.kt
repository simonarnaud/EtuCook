package sa.com.etucook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sa.com.etucook.R
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.fragments.PrincipalFragment
import sa.com.etucook.model.Ingredient
import sa.com.etucook.model.IngredientMealJoin
import sa.com.etucook.model.Meal
import sa.com.etucook.threadWorker.DataBaseThreadWorker

class Principal : AppCompatActivity() {

    private var mEtuCoockDataBase: EtuCoockDataBase? = null
    private lateinit var mDataBaseThreadWorker: DataBaseThreadWorker

    var idTestIngredient: Long? = Long.MIN_VALUE
    var idTestMeal: Long? = Long.MIN_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PrincipalFragment.newInstance())
                .commitNow()
        }

        EtuCoockDataBase.setApplication(this.application)

        mEtuCoockDataBase = EtuCoockDataBase.getInstance()
        mDataBaseThreadWorker = DataBaseThreadWorker("Thread worker")
        mDataBaseThreadWorker.start()

        val ingredient = Ingredient(null, "riz", 10F)
        val meal = Meal(null, "pâtes carbonara", 10F)

       // testIngredientTable(ingredient)
       // testMealTable(meal)
       // testIngredientMealJoinTable()
    }

    private fun testIngredientTable(ingredient: Ingredient) {
        val taskDeleteAll = Runnable {
            mEtuCoockDataBase?.ingredientDao()?.deleteAllIngredients()
        }
      //  mDataBaseThreadWorker.postTask(taskDeleteAll)

        val taskInsert = Runnable {
            mEtuCoockDataBase?.ingredientDao()?.insert(ingredient)
        }
        mDataBaseThreadWorker.postTask(taskInsert)

        val taskGetAll = Runnable {
            val ingredients = mEtuCoockDataBase?.ingredientDao()?.getAllIngredients()
            println(ingredients?.value?.size)
            println(ingredients?.value?.get(0)?.ingredientName)
           idTestIngredient = ingredients?.value?.get(0)?.id
        }
        mDataBaseThreadWorker.postTask(taskGetAll)

        val taskGetById = Runnable {
            val ingredientById = mEtuCoockDataBase?.ingredientDao()?.getIngredientById(idTestIngredient!!)
            println("Ingredient : " + ingredientById?.value?.ingredientName)
        }
        mDataBaseThreadWorker.postTask(taskGetById)
    }

    private fun testMealTable(meal: Meal) {
        val taskDeleteAll = Runnable {
            mEtuCoockDataBase?.mealDao()?.deleteAllMeals()
        }
        //mDataBaseThreadWorker.postTask(taskDeleteAll)

        val taskInsert = Runnable {
            mEtuCoockDataBase?.mealDao()?.insert(meal)
        }
        mDataBaseThreadWorker.postTask(taskInsert)

        val taskGetAll = Runnable {
            val meals = mEtuCoockDataBase?.mealDao()?.getAllMeals()
            println(meals?.value?.size)
            println(meals?.value?.get(0)?.mealName)
            idTestMeal = meals?.value?.get(0)?.id
        }
        mDataBaseThreadWorker.postTask(taskGetAll)

        val taskGetById = Runnable {
            val mealById = mEtuCoockDataBase?.mealDao()?.getMealById(idTestMeal!!)
            println("Meal : " + mealById?.mealName)
        }
        mDataBaseThreadWorker.postTask(taskGetById)
    }

    private fun testIngredientMealJoinTable() {
        val ingredientMealJoin = IngredientMealJoin(1, 1)
        println(ingredientMealJoin.ingredientId)
        println(ingredientMealJoin.mealId)
        val taskInsert = Runnable {
            mEtuCoockDataBase?.ingredientMealJoinDao()?.insert(ingredientMealJoin)
        }
        mDataBaseThreadWorker.postTask(taskInsert)

        val taskGetIngredientMealJoinByIdIngredients = Runnable {
           val ingredients = mEtuCoockDataBase?.ingredientMealJoinDao()?.getIngredientsFromMeal(1)
            println("Ingredients : " + ingredients?.get(0)?.ingredientName)
        }
        mDataBaseThreadWorker.postTask(taskGetIngredientMealJoinByIdIngredients)

        val taskGetIngredientMealJoinIdMeals = Runnable {
            val meals = mEtuCoockDataBase?.ingredientMealJoinDao()?.getMealsFromIngredient(1)
            println("Meals : " + meals?.get(0)?.mealName)
        }
        mDataBaseThreadWorker.postTask(taskGetIngredientMealJoinIdMeals)
    }

}
