package com.mycorp.twitchapprxjava.api

import com.mycorp.twitchapprxjava.api.model.gameItemDataResponse.GameItemDataDto
import com.mycorp.twitchapprxjava.api.model.topGamesResponse.TopGamesResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("kraken/games/top")
    fun loadGames(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<TopGamesResponseDto>

    @GET("kraken/channels/{id}/follows")
    fun loadGameDataItem(@Path("id") id: String): Single<GameItemDataDto>
}