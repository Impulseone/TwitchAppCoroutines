package com.mycorp.twitchapprxjava.api.controllers

import com.mycorp.twitchapprxjava.api.ApiService

class NetworkControllerImpl(private val apiService: ApiService) : NetworkController {

    override fun getDataFromNetwork() = apiService.loadGames(1, 1)

    override fun getGameItemDataFromNetwork(id: String) = apiService.loadGameDataItem(id)
}