package com.mycorp.twitchapprxjava.di

import androidx.room.Room
import com.mycorp.twitchapprxjava.database.Storage
import com.mycorp.twitchapprxjava.database.room.AppDatabase
import com.mycorp.twitchapprxjava.database.room.RoomStorage
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
    single<Storage> { RoomStorage(get(), get(), get(), get()) }
    single { get<AppDatabase>().gameDataDao }
    single { get<AppDatabase>().followersDao }
    single { get<AppDatabase>().favoriteGameDataDao }
    single { get<AppDatabase>().gameFollowersDao }
}