package com.mycorp.twitchapprxjava

import android.app.Application
import com.mycorp.api.networkModule
import com.mycorp.api.retrofitModule
import com.mycorp.database.dbModule
import com.mycorp.favorite_games.favoriteGamesModule
import com.mycorp.games.gamesModule
import com.mycorp.myapplication.repositoriesModule
import com.mycorp.rating.ratingModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    retrofitModule,
                    dbModule,
                    networkModule,
                    repositoriesModule,
                    gamesModule,
                    favoriteGamesModule,
                    ratingModule
                )
            )
        }
    }
}