package com.mycorp.myapplication

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoriesModule = module {
    single<FavoriteGamesRepository> { FavoriteGamesRepositoryImplementation(get()) }
    single<GamesRepository> { GamesRepositoryImplementation(get(), get(), androidContext()) }
    single<FollowersRepository> { FollowersRepositoryImplementation(get(), get()) }
}