package com.devhyeon.youtubelayout

import android.app.Application
import com.devhyeon.youtubelayout.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class YoutubeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@YoutubeApp)
            modules(
                listOf(
                    ViewModelModule
                )
            )
        }
    }
}