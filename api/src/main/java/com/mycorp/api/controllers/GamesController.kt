package com.mycorp.api.controllers

import com.mycorp.api.dto.topGamesResponse.TopGamesResponseDto
import io.reactivex.Single

interface GamesController {
    fun getDataFromNetwork(limit: Int, offset: Int): Single<TopGamesResponseDto>
    suspend fun getDataFromNetworkSuspend(limit: Int, offset: Int): TopGamesResponseDto
}