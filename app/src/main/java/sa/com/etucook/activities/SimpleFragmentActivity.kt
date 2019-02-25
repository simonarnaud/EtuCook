package sa.com.etucook.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.ingredient_activity.*

abstract class SimpleFragmentActivity: AppCompatActivity() {

    protected lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingredient_activity)

        navController = Navigation.findNavController(this, R.id.nav_fragment)

        setSupportActionBar(toolbar_activity)
        toolbar_activity.setupWithNavController(navController, AppBarConfiguration(navController.graph))
    }
}