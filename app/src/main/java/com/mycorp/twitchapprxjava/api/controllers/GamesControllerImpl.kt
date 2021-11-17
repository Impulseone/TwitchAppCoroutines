package com.mycorp.twitchapprxjava.api.controllers

import com.mycorp.twitchapprxjava.api.FollowersApi
import com.mycorp.twitchapprxjava.api.GamesApi

class GamesControllerImpl(private val gamesApi: GamesApi) : GamesController {
    override fun getDataFromNetwork(limit: Int, offset: Int) = gamesApi.loadGames(limit, offset)
}