package com.mycorp.twitchapprxjava.data.repository

import com.mycorp.twitchapprxjava.data.network.NetworkController
import com.mycorp.twitchapprxjava.domain.repository.Repository

class RepositoryImplementation(
    private val networkController: NetworkController
) : Repository {

    override fun getGamesDataFromNetwork() = networkController.getDataFromNetwork()
}