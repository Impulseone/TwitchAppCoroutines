package com.mycorp.api

import com.mycorp.api.dto.topGamesResponse.TopGamesResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {
    @GET("kraken/games/top")
    fun loadGames(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<TopGamesResponseDto>

    @GET("kraken/games/top")
    suspend fun loadGamesSuspend(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): TopGamesResponseDto
}