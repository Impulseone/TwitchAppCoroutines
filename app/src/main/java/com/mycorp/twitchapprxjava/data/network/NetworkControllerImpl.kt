package com.mycorp.twitchapprxjava.data.network

import com.mycorp.twitchapprxjava.data.network.retrofit.ApiService
import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponseDto
import io.reactivex.Single

class NetworkControllerImpl(val apiService: ApiService) : NetworkController {
    override fun getDataFromNetwork(): Single<TwitchResponseDto> {
        return apiService.loadGames()
    }
}