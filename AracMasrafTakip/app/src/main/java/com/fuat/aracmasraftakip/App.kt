package com.fuat.aracmasraftakip

import android.app.Application
import com.fuat.aracmasraftakip.utils.ContextProvider

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ContextProvider.init(this)
    }
}
