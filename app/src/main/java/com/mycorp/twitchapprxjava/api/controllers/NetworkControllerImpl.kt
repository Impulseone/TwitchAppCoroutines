package com.mycorp.twitchapprxjava.api.controllers

import com.mycorp.twitchapprxjava.api.ApiService

class NetworkControllerImpl(private val apiService: ApiService) : NetworkController {

    override fun getDataFromNetwork(limit: Int, offset: Int) = apiService.loadGames(limit, offset)

    override fun getGameItemDataFromNetwork(id: String) = apiService.loadGameDataItem(id)
}