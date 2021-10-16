package com.mycorp.twitchapprxjava.data.repository

import com.mycorp.twitchapprxjava.data.network.NetworkController
import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import com.mycorp.twitchapprxjava.domain.repository.Repository

class RepositoryImplementation(
    private val networkController: NetworkController,
    private val storage: Storage
) : Repository {

    override fun getGamesDataFromNetwork() = networkController.getDataFromNetwork()

    override fun getGamesDataFromDb() = storage.getGamesDataFromDb()

    override fun insertGamesDataToDb(gameDataTables: List<GameDataTable>) =
        storage.insertGamesData(gamesData = gameDataTables)
}