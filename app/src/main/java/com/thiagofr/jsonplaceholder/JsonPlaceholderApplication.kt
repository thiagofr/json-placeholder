package com.thiagofr.jsonplaceholder

import android.app.Application
import com.thiagofr.jsonplaceholder.di.networkModule
import com.thiagofr.jsonplaceholder.di.useCaseModule
import org.koin.core.context.startKoin

class JsonPlaceholderApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            networkModule
            useCaseModule
        }
    }

}