package sa.com.etucook.app_manager

import android.app.Application
import sa.com.etucook.database.EtuCoockDataBase

class EtuCookApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        EtuCoockDataBase.setApplication(this)
    }
}