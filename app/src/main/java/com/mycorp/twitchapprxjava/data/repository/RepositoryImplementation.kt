package com.mycorp.twitchapprxjava.data.repository

import com.mycorp.twitchapprxjava.data.network.NetworkController
import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import com.mycorp.twitchapprxjava.domain.repository.Repository
import io.reactivex.Single

class RepositoryImplementation(
    private val networkController: NetworkController,
    private val storage: Storage
) : Repository {

    override fun getGamesDataFromNetwork(): Single<List<GameData>> {
        val gameData: Single<List<GameData>> =
            networkController.getDataFromNetwork().map { it: TwitchResponse ->
                it.toListOfGameData()
            }
        return gameData
    }

    override fun getGamesDataFromDb(): Single<List<GameData>> {
        val gameData:Single<List<GameData>> = storage.getGamesDataFromDb().map {
            it.map { gameDataEntity -> gameDataEntity.toGameData() }
        }
        return gameData
    }


    override fun insertGamesDataToDb(gameDataTables: List<GameData>) =
        storage.insertGamesData(gamesData = gameDataTables)
}