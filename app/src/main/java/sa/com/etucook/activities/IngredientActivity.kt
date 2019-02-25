package sa.com.etucook.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import sa.com.etucook.fragments.IngredientFragment

class IngredientActivity: SimpleFragmentActivity(), IngredientFragment.OnInteractionListener {
    companion object {
        fun getIntent(context: Context, ingredientUri: Uri?) = Intent(context, IngredientActivity::class.java).apply {
            data = ingredientUri
        }
    }

    private var ingredientUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ingredientUri = intent.data
    }

  //  override fun createFragment() = IngredientFragment.newInstance(ingredientUri)
  //  override fun getLayoutResId() = R.layout.ingredient_activity

    override fun onIngredientSaved() = finish()
    override fun onIngredientDeleted() = finish()
}