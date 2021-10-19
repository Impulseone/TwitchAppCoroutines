package com.mycorp.twitchapprxjava.data.network

import com.mycorp.twitchapprxjava.data.network.retrofit.Common
import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import io.reactivex.Observable
import io.reactivex.Single

class NetworkControllerImpl : NetworkController {
    override fun getDataFromNetwork(): Single<TwitchResponse> {
        return Common.retrofitService.loadGames()
    }
}