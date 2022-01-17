package com.thefear.myfilms

import android.app.Application
import com.thefear.myfilms.model.entities.FilmsFeed
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    val filmsFeed = FilmsFeed()

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(appModule)
        }
    }
}