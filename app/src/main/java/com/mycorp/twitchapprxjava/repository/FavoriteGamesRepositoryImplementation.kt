package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.database.Storage
import com.mycorp.twitchapprxjava.database.model.SingleGameData

class FavoriteGamesRepositoryImplementation(
    private val storage: Storage
) : FavoriteGamesRepository {
    override fun getFavoriteGamesFromDb() = storage.getFavoriteGamesFromDb()
        .map {
            SingleGameData.fromSingleGameDataEntity(it)
        }
}