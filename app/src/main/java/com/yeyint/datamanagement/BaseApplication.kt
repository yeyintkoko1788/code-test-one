package com.yeyint.datamanagement

import android.app.Application
import android.content.Context

class BaseApplication : Application() {
    companion object {
        private lateinit var instance: BaseApplication

        fun getInstance(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}