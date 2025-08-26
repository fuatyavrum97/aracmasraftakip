package com.fuat.aracmasraftakip.utils

import android.content.Context

object ContextProvider {
    private lateinit var appContext: Context

    internal fun init(context: Context) {
        appContext = context.applicationContext
    }

    fun getContext(): Context = appContext
}