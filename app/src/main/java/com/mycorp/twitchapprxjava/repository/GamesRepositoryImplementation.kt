package com.mycorp.twitchapprxjava.repository

import android.content.Context
import com.mycorp.twitchapprxjava.api.controllers.GamesController
import com.mycorp.twitchapprxjava.database.storage.GamesStorage
import com.mycorp.twitchapprxjava.models.GameData
import com.mycorp.twitchapprxjava.models.ListItemData
import com.mycorp.twitchapprxjava.screens.games.adapter.GameListItem

class GamesRepositoryImplementation(
    private val gamesController: GamesController,
    private val gamesStorage: GamesStorage,
    private val context: Context
) : GamesRepository {
    override fun fetchGamesDataList(limit: Int, offset: Int) =
        gamesController.getDataFromNetwork(limit, offset).map {
            it.toListOfGameData()
        }

    override fun getGamesData() = gamesStorage.getGamesData().map {
        ListItemData(it.id, GameListItem(context, GameData.fromEntity(it)))
    }

    override fun getGameDataById(id: String) =
        gamesStorage.getGameDataEntityById(id).map {
            GameData.fromEntity(it)
        }

    override fun insertGamesData(gameDataList: List<GameData>) =
        gamesStorage.insertGamesData(gamesData = gameDataList)
}