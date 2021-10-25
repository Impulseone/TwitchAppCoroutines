package com.mycorp.twitchapprxjava.data.network.retrofit

import com.mycorp.twitchapprxjava.data.storage.model.GameItemDataDto
import com.mycorp.twitchapprxjava.data.storage.model.topGamesResponse.TopGamesResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

const val acceptHeader = "Accept: application/vnd.twitchtv.v5+json"
const val clientId = "Client-ID: ahuoi1tl0qmqbyi8jo8nitbmuaad7w"

interface ApiService {
    @Headers(acceptHeader, clientId)
    @GET("kraken/games/top")
    fun loadGames(): Single<TopGamesResponseDto>

    @Headers(acceptHeader, clientId)
    @GET("kraken/channels/{id}/follows")
    fun loadGameDataItem(@Path("id") id: String ): Single<GameItemDataDto>
}