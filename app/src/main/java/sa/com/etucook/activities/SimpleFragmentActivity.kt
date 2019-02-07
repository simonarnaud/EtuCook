package sa.com.etucook.activities

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import sa.com.etucook.R
import sa.com.etucook.database.EtuCoockDataBase

abstract class SimpleFragmentActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EtuCoockDataBase.setApplication(this.application)

        setContentView(getLayoutResId())

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar_activity))
        supportActionBar?.setIcon(R.mipmap.ic_launcher)

        if (supportFragmentManager.findFragmentById(R.id.container_fragment) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_fragment, createFragment())
                .commit()
        }
    }

    protected abstract fun createFragment(): Fragment
    @LayoutRes
    protected abstract fun getLayoutResId(): Int

}