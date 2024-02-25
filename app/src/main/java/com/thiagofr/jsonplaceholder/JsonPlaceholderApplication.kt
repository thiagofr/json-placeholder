package com.thiagofr.jsonplaceholder

import android.app.Application
import com.thiagofr.jsonplaceholder.di.networkModule
import com.thiagofr.jsonplaceholder.di.useCaseModule
import com.thiagofr.jsonplaceholder.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JsonPlaceholderApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JsonPlaceholderApplication)
            modules(
                networkModule,
                useCaseModule,
                viewModelModule,
            )
        }
    }

}