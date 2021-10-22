package com.mycorp.twitchapprxjava.data.repository

import com.mycorp.twitchapprxjava.data.network.NetworkController
import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponseDto
import com.mycorp.twitchapprxjava.domain.repository.Repository
import io.reactivex.Single

class RepositoryImplementation(
    private val networkController: NetworkController,
    private val storage: Storage
) : Repository {

    override fun getGamesDataFromNetwork() =
        networkController.getDataFromNetwork().map {
            it.toListOfGameData()
        }

    override fun getGamesDataFromDb() = storage.getGamesDataFromDb().map {
        it.map { it.toGameData() }
    }


    override fun insertGamesDataToDb(gameDataTables: List<GameData>) =
        storage.insertGamesData(gamesData = gameDataTables)
}