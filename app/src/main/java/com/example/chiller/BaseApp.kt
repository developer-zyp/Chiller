package com.example.chiller

import android.app.Application
import android.content.Context

class BaseApp : Application() {
    companion object {
        val TAG = BaseApp::class.java.simpleName
        private lateinit var instance: BaseApp

        fun getInstance(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
//        FirebaseApp.initializeApp(this)
//
//        val config: PRDownloaderConfig = PRDownloaderConfig.newBuilder()
//            .setDatabaseEnabled(true)
//            .build()
//        PRDownloader.initialize(this, config)
    }
}