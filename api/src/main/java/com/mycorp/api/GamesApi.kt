package com.mycorp.api

import com.mycorp.api.dto.topGamesResponse.TopGamesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {
    @GET("kraken/games/top")
    suspend fun loadGames(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): TopGamesResponseDto
}