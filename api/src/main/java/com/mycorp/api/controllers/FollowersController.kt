package com.mycorp.api.controllers

import com.mycorp.api.dto.gameItemDataResponse.GameItemDataDto
import io.reactivex.Single

interface FollowersController {
    fun getGameItemDataFromNetwork(id: String): Single<GameItemDataDto>
    suspend fun getGameItemDataFromNetworkSuspend(id: String): GameItemDataDto
}