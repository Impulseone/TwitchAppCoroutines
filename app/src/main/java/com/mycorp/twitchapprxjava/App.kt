package com.mycorp.twitchapprxjava

import android.app.Application
import com.mycorp.twitchapprxjava.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(listOf(
                retrofitModule,
                dbModule,
                networkModule,
                repositoriesModule,
                viewModelModule,
                pagingModule,
            ))
        }
    }
}