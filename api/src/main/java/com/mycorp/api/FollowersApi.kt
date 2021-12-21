package com.mycorp.api

import com.mycorp.api.dto.gameItemDataResponse.GameItemDataDto
import retrofit2.http.GET
import retrofit2.http.Path

interface FollowersApi {
    @GET("kraken/channels/{id}/follows")
    suspend fun loadGameDataItem(@Path("id") id: String): GameItemDataDto
}