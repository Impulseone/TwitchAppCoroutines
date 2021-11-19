package com.mycorp.twitchapprxjava.usecases

import androidx.paging.DataSource
import com.mycorp.twitchapprxjava.models.GameData
import com.mycorp.twitchapprxjava.models.ListItemData
import com.mycorp.twitchapprxjava.screens.games.adapter.GameListItem
import io.reactivex.Single

interface GameDataUseCase {
    fun getGames(): DataSource.Factory<Int, ListItemData<GameListItem>>
    fun fetchGameData(gameId: String): Single<Triple<Int, GameData, String>>
    fun getGameData(gameId: String): Single<Triple<Int, GameData, String>>
}