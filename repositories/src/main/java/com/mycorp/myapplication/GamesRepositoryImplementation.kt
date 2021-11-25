package com.mycorp.myapplication

import android.content.Context
import com.mycorp.api.controllers.GamesController
import com.mycorp.database.storage.GamesStorage
import com.mycorp.model.GameData

class GamesRepositoryImplementation(
    private val gamesController: GamesController,
    private val gamesStorage: GamesStorage,
    private val context: Context
) : GamesRepository {
    override fun fetchGamesDataList(limit: Int, offset: Int) =
        gamesController.getDataFromNetwork(limit, offset).map {
            it.toModel()
        }!!

    override fun getGamesLimited(limit: Int, offset: Int) =
        gamesStorage.getGamesLimited(limit, offset).map {
            it.map { gameDataEntity ->
                gameDataEntity.toModel()
            }
        }

    override fun getGameDataById(id: String) =
        gamesStorage.getGameDataEntityById(id).map {
            it.toModel()
        }

    override fun insertGamesData(gameDataList: List<GameData>) =
        gamesStorage.insertGamesData(gamesData = gameDataList)
}