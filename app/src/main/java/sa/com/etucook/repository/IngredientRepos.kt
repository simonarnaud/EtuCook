package sa.com.etucook.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.Ingredient
import sa.com.etucook.threadWorker.DataBaseThreadWorker

class IngredientRepos(application: Application) {

    private var mEtuCoockDataBase: EtuCoockDataBase
    private var mDataBaseThreadWorker = DataBaseThreadWorker("Thread worker")

    var ingredients: LiveData<List<Ingredient>>

    init {
        EtuCoockDataBase.setApplication(application)
        mEtuCoockDataBase = EtuCoockDataBase.getInstance()
        ingredients = mEtuCoockDataBase.ingredientDao().getAllIngredients()
        mDataBaseThreadWorker.start()
    }

    fun insertIngredient(ingredient: Ingredient) {
        val task = Runnable { mEtuCoockDataBase.ingredientDao().insert(ingredient) }
        mDataBaseThreadWorker.postTask(task)
    }

    fun deleteIngredient(id: Long?) {
        val task = Runnable { mEtuCoockDataBase.ingredientDao().deleteIngredient(id) }
        mDataBaseThreadWorker.postTask(task)
    }

    fun deleteAllIngredients() {
        val task = Runnable { mEtuCoockDataBase.ingredientDao().deleteAllIngredients() }
        mDataBaseThreadWorker.postTask(task)
    }

    fun updateIngredient(ingredient: Ingredient) {
        val task = Runnable { mEtuCoockDataBase.ingredientDao().updateIngredient(ingredient) }
        mDataBaseThreadWorker.postTask(task)
    }

    fun getAllIngredients(): LiveData<List<Ingredient>> {
        return ingredients
    }

    fun getIngredientById(id: Long?): LiveData<Ingredient>/*Ingredient?*/ {
        //return ingredients.value?.filter { it.id == id }?.get(0)
        return mEtuCoockDataBase.ingredientDao().getIngredientById(id)
    }
}