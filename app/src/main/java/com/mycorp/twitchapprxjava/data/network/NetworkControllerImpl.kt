package com.mycorp.twitchapprxjava.data.network

import com.mycorp.twitchapprxjava.data.network.retrofit.ApiService

class NetworkControllerImpl(private val apiService: ApiService) : NetworkController {

    override fun getDataFromNetwork() = apiService.loadGames()

    override fun getGameItemDataFromNetwork(id: String) = apiService.loadGameDataItem(id)
}