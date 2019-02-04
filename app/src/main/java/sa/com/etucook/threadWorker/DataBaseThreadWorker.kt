package sa.com.etucook.threadWorker

import android.os.Handler
import android.os.HandlerThread

class DataBaseThreadWorker(threadName: String): HandlerThread(threadName) {

    private lateinit var mThreadWorker: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mThreadWorker = Handler(looper)
    }

    fun postTask(task: Runnable) {
        mThreadWorker.post(task)
    }
}