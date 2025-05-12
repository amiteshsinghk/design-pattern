package com.amitesh.designpattern

import android.app.Application
import com.amitesh.designpattern.manualDi.di.AppContainer

class MyApplication: Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer()
    }
}