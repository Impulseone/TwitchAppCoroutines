package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.api.controllers.NetworkController
import com.mycorp.twitchapprxjava.database.Storage
import com.mycorp.twitchapprxjava.database.model.GameData

class GamesRepositoryImplementation(
    private val networkController: NetworkController,
    private val storage: Storage
) : GamesRepository {
    override fun getGamesDataListFromServer(limit: Int, offset: Int) =
        networkController.getDataFromNetwork(limit, offset).map {
            it.toListOfGameData()
        }

    override fun getGamesDataFromDb() = storage.getGamesDataFromDb().map {
        it.map { GameData.fromEntity(it) }
    }

    override fun getGameDataById(id: String) =
        storage.getGameDataEntityById(id).map {
            GameData.fromEntity(it)
        }

    override fun insertGamesDataToDb(gameDataList: List<GameData>) =
        storage.insertGamesData(gamesData = gameDataList)
}