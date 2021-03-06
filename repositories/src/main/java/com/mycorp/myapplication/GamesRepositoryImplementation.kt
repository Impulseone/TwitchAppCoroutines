package com.mycorp.myapplication

import com.mycorp.api.controllers.GamesController
import com.mycorp.database.storage.GamesStorage
import com.mycorp.model.GameData

class GamesRepositoryImplementation(
    private val gamesController: GamesController,
    private val gamesStorage: GamesStorage,
) : GamesRepository {
    override suspend fun fetchGamesDataList(limit: Int, offset: Int) =
        gamesController.getDataFromNetwork(limit, offset).toModel()

    override suspend fun deleteAllGames() {
        gamesStorage.deleteAllGames()
    }

    override fun getGamesPaging() = gamesStorage.getGamesDataPaging()

    override suspend fun getGameDataById(id: String) =
        gamesStorage.getGameDataEntityById(id).toModel()

    override suspend fun insertGamesData(gameDataList: List<GameData>) =
        gamesStorage.insertGamesData(gameDataList)
}