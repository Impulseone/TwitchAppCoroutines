package com.mycorp.api.controllers

import com.mycorp.api.dto.gameItemDataResponse.GameItemDataDto

interface FollowersController {
    suspend fun getGameItemDataFromNetwork(id: String): GameItemDataDto
}