package com.fuat.aracmasraftakip.utils

import android.content.Context

object ContextProvider {
    private lateinit var appContext: Context

    /** Uygulama ayağa kalkarken buraya atama yapılacak */
    internal fun init(context: Context) {
        appContext = context.applicationContext
    }

    /** Her yerden çağırabileceğin global Context */
    fun getContext(): Context = appContext
}