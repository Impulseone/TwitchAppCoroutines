package com.mycorp.twitchapprxjava.di

import androidx.room.Room
import com.mycorp.twitchapprxjava.database.AppDatabase
import com.mycorp.twitchapprxjava.database.storage.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "games_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single<GamesStorage> { GamesStorageImplementation(get()) }
    single<FollowersStorage> { FollowersStorageImplementation(get(), get()) }
    single<FavoriteGamesStorage> { FavoriteGamesStorageImplementation(get()) }
    single { get<AppDatabase>().gameDataDao }
    single { get<AppDatabase>().followersDao }
    single { get<AppDatabase>().favoriteGameDataDao }
    single { get<AppDatabase>().gameFollowersDao }
}