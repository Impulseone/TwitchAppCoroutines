package com.mycorp.twitchapprxjava.data.repository

import com.mycorp.twitchapprxjava.data.network.NetworkController
import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import com.mycorp.twitchapprxjava.domain.repository.Repository
import io.reactivex.Observable

class RepositoryImplementation(
    private val networkController: NetworkController,
    private val storage: Storage
) : Repository {

    override fun getGamesDataFromNetwork() = networkController.getDataFromNetwork()

    override fun getGamesDataFromDb() = storage.getGamesDataFromDb()

}