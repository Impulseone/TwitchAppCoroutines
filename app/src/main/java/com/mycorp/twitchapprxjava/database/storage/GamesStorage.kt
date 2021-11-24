package com.mycorp.twitchapprxjava.database.storage

import androidx.paging.DataSource
import com.mycorp.model.GameData
import com.mycorp.twitchapprxjava.database.entities.GameDataEntity
import io.reactivex.Completable
import io.reactivex.Single

interface GamesStorage {
    fun getGamesData(): DataSource.Factory<Int, GameDataEntity>
    fun getGameDataEntityById(id: String): Single<GameDataEntity>
    fun insertGamesData(gamesData: List<GameData>): Completable
}