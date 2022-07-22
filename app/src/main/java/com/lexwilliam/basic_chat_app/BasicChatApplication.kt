package com.lexwilliam.basic_chat_app

import android.app.Application
import timber.log.Timber

class BasicChatApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}