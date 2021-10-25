package com.mycorp.twitchapprxjava.data.network

import com.mycorp.twitchapprxjava.data.storage.model.GameItemDataDto
import com.mycorp.twitchapprxjava.data.storage.model.topGamesResponse.TopGamesResponseDto
import io.reactivex.Single

interface NetworkController {
   fun getDataFromNetwork(): Single<TopGamesResponseDto>
   fun getGameItemDataFromNetwork(id:String): Single<GameItemDataDto>
}