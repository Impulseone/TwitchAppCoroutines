package com.mycorp.twitchapprxjava.data.network

import com.mycorp.twitchapprxjava.data.storage.model.GameItemDataDto
import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponseDto
import io.reactivex.Single

interface NetworkController {
   fun getDataFromNetwork(): Single<TwitchResponseDto>
   fun getGameItemDataFromNetwork(id:String): Single<GameItemDataDto>
}