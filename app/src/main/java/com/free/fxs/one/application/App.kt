package com.free.fxs.one.application

import android.app.Application
import com.free.fxs.one.common.BaseCrashHandler

class App : Application() {

    companion object {
        lateinit var mContext: App
    }

    override fun onCreate() {
        super.onCreate()
        BaseCrashHandler.getInstance()
        mContext = this
    }
}
