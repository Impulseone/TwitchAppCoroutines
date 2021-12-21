package com.mycorp.api.controllers

import com.mycorp.api.dto.topGamesResponse.TopGamesResponseDto

interface GamesController {
    suspend fun getDataFromNetwork(limit: Int, offset: Int): TopGamesResponseDto
}