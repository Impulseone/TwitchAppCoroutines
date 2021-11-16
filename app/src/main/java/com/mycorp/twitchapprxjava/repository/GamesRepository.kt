package com.mycorp.twitchapprxjava.repository

import androidx.paging.DataSource
import com.mycorp.twitchapprxjava.models.GameData
import com.mycorp.twitchapprxjava.models.ListItemData
import com.mycorp.twitchapprxjava.screens.games.adapter.GameListItem
import io.reactivex.Completable
import io.reactivex.Single

interface GamesRepository {

    fun fetchGamesDataList(limit: Int, offset: Int): Single<List<GameData>>

    fun getGamesData(): DataSource.Factory<Int, ListItemData<GameListItem>>
    fun getGameDataById(id: String): Single<GameData>

    fun insertGamesData(gameDataList: List<GameData>): Completable
}