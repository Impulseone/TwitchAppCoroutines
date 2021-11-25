package com.mycorp.myapplication

import com.mycorp.model.GameData
import io.reactivex.Completable
import io.reactivex.Single

interface GamesRepository {

    fun fetchGamesDataList(limit: Int, offset: Int): Single<List<GameData>>

    fun getGamesLimited(limit: Int, offset: Int): Single<List<GameData>>
    fun getGameDataById(id: String): Single<GameData>

    fun insertGamesData(gameDataList: List<GameData>): Completable
}