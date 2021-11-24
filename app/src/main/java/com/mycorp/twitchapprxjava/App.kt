package com.mycorp.twitchapprxjava

import android.app.Application
import com.mycorp.twitchapprxjava.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
//            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidLogger()
            androidContext(this@App)
            modules(listOf(
                retrofitModule,
                dbModule,
                networkModule,
                repositoriesModule,
                viewModelModule,
                pagingModule,
                usecasesModule
            ))
        }
    }
}