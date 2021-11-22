package com.mycorp.twitchapprxjava.api

import com.mycorp.twitchapprxjava.api.dto.topGamesResponse.TopGamesResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {
    @GET("kraken/games/top")
    fun loadGames(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<TopGamesResponseDto>
}