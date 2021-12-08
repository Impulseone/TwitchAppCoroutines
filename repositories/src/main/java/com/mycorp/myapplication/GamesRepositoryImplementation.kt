package com.mycorp.myapplication

import com.mycorp.api.controllers.GamesController
import com.mycorp.database.storage.GamesStorage
import com.mycorp.model.GameData

class GamesRepositoryImplementation(
    private val gamesController: GamesController,
    private val gamesStorage: GamesStorage,
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

    override suspend fun getGameDataByIdSuspend(id: String) = gamesStorage.getGameDataEntityByIdSuspend(id).toModel()

    override fun insertGamesData(gameDataList: List<GameData>) =
        gamesStorage.insertGamesData(gamesData = gameDataList)
}