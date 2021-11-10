package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.database.Storage
import com.mycorp.twitchapprxjava.database.model.SingleGameData
import io.reactivex.Single

class FavoriteGamesRepositoryImplementation(
    private val storage: Storage
) : FavoriteGamesRepository {
    override fun getFavoriteGamesFromDb() = storage.getFavoriteGamesFromDb()
        .map {
            SingleGameData.fromSingleGameDataEntity(it)
        }
}