package com.mycorp.twitchapprxjava.repository

import android.content.Context
import com.mycorp.twitchapprxjava.api.controllers.NetworkController
import com.mycorp.twitchapprxjava.database.Storage
import com.mycorp.twitchapprxjava.models.GameData
import com.mycorp.twitchapprxjava.screens.games.adapter.GameListItem

class GamesRepositoryImplementation(
    private val networkController: NetworkController,
    private val storage: Storage,
    private val context: Context
) : GamesRepository {
    override fun fetchGamesDataList(limit: Int, offset: Int) =
        networkController.getDataFromNetwork(limit, offset).map {
            it.toListOfGameData()
        }

    override fun getGamesData() = storage.getGamesData().map {
        GameListItem(context, GameData.fromEntity(it))
    }

    override fun getGameDataById(id: String) =
        storage.getGameDataEntityById(id).map {
            GameData.fromEntity(it)
        }

    override fun insertGamesData(gameDataList: List<GameData>) =
        storage.insertGamesData(gamesData = gameDataList)
}