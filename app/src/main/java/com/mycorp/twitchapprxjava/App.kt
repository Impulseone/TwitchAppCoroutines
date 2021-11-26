package com.mycorp.twitchapprxjava

import android.app.Application
import com.mycorp.api.networkModule
import com.mycorp.api.retrofitModule
import com.mycorp.database.dbModule
import com.mycorp.favorite_games.favoriteGamesModule
import com.mycorp.games.pagingModule
import com.mycorp.games.usecasesModule
import com.mycorp.games.viewModelModule
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
                usecasesModule,
                favoriteGamesModule
            ))
        }
    }
}