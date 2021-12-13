package com.mycorp.api.controllers

import com.mycorp.api.GamesApi

class GamesControllerImpl(private val gamesApi: GamesApi) : GamesController {
    override fun getDataFromNetwork(limit: Int, offset: Int) = gamesApi.loadGames(limit, offset)
    override suspend fun getDataFromNetworkSuspend(limit: Int, offset: Int) = gamesApi.loadGamesSuspend(limit, offset)
}