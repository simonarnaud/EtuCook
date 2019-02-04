package sa.com.etucook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import sa.com.etucook.database.EtuCoockDataBase
import sa.com.etucook.model.Ingredient
import sa.com.etucook.threadWorker.DataBaseThreadWorker

class PrincipalViewModel(application: Application) : AndroidViewModel(application) {

    private val _data = MutableLiveData<String>()
    private val mEtuCoockDataBase: EtuCoockDataBase
    private val mDataBaseThreadWorker = DataBaseThreadWorker("Thread worker")

    var ingredients: LiveData<List<Ingredient>>

    init {
        _data.value = "Hello, JetPack!"

        EtuCoockDataBase.setApplication(application)
        mEtuCoockDataBase = EtuCoockDataBase.getInstance()
        ingredients = mEtuCoockDataBase.ingredientDao().getAllIngredients()
        mDataBaseThreadWorker.start()
    }

    fun getListIngredients(): LiveData<List<Ingredient>> {
        return ingredients
    }

    fun addIngredient(ingredient: Ingredient) {
        val taskInsert = Runnable {
            mEtuCoockDataBase.ingredientDao().insert(ingredient)
        }
        mDataBaseThreadWorker.postTask(taskInsert)
    }


}
