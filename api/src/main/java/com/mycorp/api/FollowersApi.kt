package com.mycorp.api

import com.mycorp.api.dto.gameItemDataResponse.GameItemDataDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface FollowersApi {
    @GET("kraken/channels/{id}/follows")
    fun loadGameDataItem(@Path("id") id: String): Single<GameItemDataDto>

    @GET("kraken/channels/{id}/follows")
    suspend fun loadGameDataItemSuspend(@Path("id") id: String): GameItemDataDto
}