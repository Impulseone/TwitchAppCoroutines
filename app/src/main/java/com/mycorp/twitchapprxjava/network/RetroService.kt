package com.mycorp.twitchapprxjava.network

import com.mycorp.twitchapprxjava.model.TwitchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers


interface RetroService {

    @Headers("Accept: application/vnd.twitchtv.v5+json", "Client-ID: ahuoi1tl0qmqbyi8jo8nitbmuaad7w")
    @GET("kraken/games/top")
    fun loadGames(): Observable<TwitchResponse>
}