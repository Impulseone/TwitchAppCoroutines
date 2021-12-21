package com.mycorp.myapplication

import org.koin.dsl.module

val repositoriesModule = module {
    single<FavoriteGamesRepository> { FavoriteGamesRepositoryImplementation(get()) }
    single<GamesRepository> { GamesRepositoryImplementation(get(), get()) }
    single<FollowersRepository> { FollowersRepositoryImplementation(get(), get()) }
}