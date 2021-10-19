package com.mycorp.twitchapprxjava.data.network.retrofit

import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

const val acceptHeader = "Accept: application/vnd.twitchtv.v5+json"
const val clientId = "Client-ID: ahuoi1tl0qmqbyi8jo8nitbmuaad7w"

interface ApiService {
    @Headers(acceptHeader, clientId)
    @GET("kraken/games/top")
    fun loadGames(): Single<TwitchResponse>
}