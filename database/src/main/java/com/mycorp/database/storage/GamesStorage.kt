package com.mycorp.database.storage

import androidx.paging.DataSource
import com.mycorp.database.entities.GameDataEntity
import com.mycorp.model.GameData
import io.reactivex.Completable
import io.reactivex.Single

interface GamesStorage {
    fun getGamesData(): DataSource.Factory<Int, GameDataEntity>
    fun getGameDataEntityById(id: String): Single<GameDataEntity>
    fun insertGamesData(gamesData: List<GameData>): Completable
}