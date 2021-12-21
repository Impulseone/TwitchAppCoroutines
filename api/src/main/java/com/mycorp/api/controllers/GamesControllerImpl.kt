package com.mycorp.api.controllers

import com.mycorp.api.GamesApi

class GamesControllerImpl(private val gamesApi: GamesApi) : GamesController {
    override suspend fun getDataFromNetwork(limit: Int, offset: Int) = gamesApi.loadGames(limit, offset)
}