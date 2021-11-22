package com.mycorp.twitchapprxjava.api.controllers

import com.mycorp.twitchapprxjava.api.dto.topGamesResponse.TopGamesResponseDto
import io.reactivex.Single

interface GamesController {
    fun getDataFromNetwork(limit: Int, offset: Int): Single<TopGamesResponseDto>
}