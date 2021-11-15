package com.mycorp.twitchapprxjava.repository

import androidx.paging.DataSource
import com.mycorp.twitchapprxjava.database.model.GameData
import com.mycorp.twitchapprxjava.screens.games.adapter.GameListItem
import io.reactivex.Completable
import io.reactivex.Single

interface GamesRepository {

    fun getGamesDataListFromServer(limit: Int, offset: Int): Single<List<GameData>>
    fun getGamesDataFromDb(): DataSource.Factory<Int, GameListItem>
    fun getGameDataById(id: String): Single<GameData>

    fun insertGamesDataToDb(gameDataList: List<GameData>): Completable
}