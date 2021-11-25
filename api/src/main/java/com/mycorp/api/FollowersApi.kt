package com.mycorp.api

import com.mycorp.twitchapprxjava.api.dto.gameItemDataResponse.GameItemDataDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface FollowersApi {
    @GET("kraken/channels/{id}/follows")
    fun loadGameDataItem(@Path("id") id: String): Single<GameItemDataDto>
}