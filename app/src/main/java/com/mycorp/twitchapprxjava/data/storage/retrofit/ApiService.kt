package com.mycorp.twitchapprxjava.data.storage.retrofit

import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("Accept: application/vnd.twitchtv.v5+json", "Client-ID: ahuoi1tl0qmqbyi8jo8nitbmuaad7w")
    @GET("kraken/games/top")
   suspend fun loadGames(): TwitchResponse
}