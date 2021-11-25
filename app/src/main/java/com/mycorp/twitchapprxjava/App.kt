package com.mycorp.twitchapprxjava

import android.app.Application
import com.mycorp.api.networkModule
import com.mycorp.api.retrofitModule
import com.mycorp.database.dbModule
import com.mycorp.features.pagingModule
import com.mycorp.features.usecasesModule
import com.mycorp.features.viewModelModule
import com.mycorp.myapplication.repositoriesModule
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
                usecasesModule
            ))
        }
    }
}