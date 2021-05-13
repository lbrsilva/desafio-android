package br.com.lbrsilva.nasa

import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import java.lang.ref.WeakReference

@HiltAndroidApp
class NasaApplication : MultiDexApplication() {
    companion object {
        private lateinit var weakContext: WeakReference<Context>

        fun context(): Context {
            return weakContext.get()!!
        }
    }

    override fun onCreate() {
        super.onCreate()

        weakContext = WeakReference(this)
    }
}