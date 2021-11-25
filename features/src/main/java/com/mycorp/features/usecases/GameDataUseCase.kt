package com.mycorp.features.usecases

import androidx.paging.DataSource
import com.mycorp.model.FollowerInfo
import com.mycorp.model.GameData
import com.mycorp.model.ListItemData
import com.mycorp.features.screens.games.adapter.GameListItem
import io.reactivex.Single

interface GameDataUseCase {
    fun getGames(): DataSource.Factory<Int, ListItemData<GameListItem>>
    fun fetchGameData(gameId: String): Single<Triple<Int, GameData, List<FollowerInfo>>>
    fun getGameData(gameId: String): Single<Triple<Int, GameData, List<FollowerInfo>>>
}