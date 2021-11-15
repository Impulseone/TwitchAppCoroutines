package com.mycorp.twitchapprxjava.api.controllers

import com.mycorp.twitchapprxjava.api.dto.gameItemDataResponse.GameItemDataDto
import com.mycorp.twitchapprxjava.api.dto.topGamesResponse.TopGamesResponseDto
import io.reactivex.Single

interface NetworkController {
    fun getDataFromNetwork(limit: Int, offset: Int): Single<TopGamesResponseDto>
    fun getGameItemDataFromNetwork(id: String): Single<GameItemDataDto>
}