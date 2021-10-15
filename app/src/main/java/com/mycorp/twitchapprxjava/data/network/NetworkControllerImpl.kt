package com.mycorp.twitchapprxjava.data.network

import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import com.mycorp.twitchapprxjava.data.storage.retrofit.Common
import io.reactivex.Observable

class NetworkControllerImpl : NetworkController {
    override fun getDataFromNetwork(): Observable<TwitchResponse> {
        return Common.retrofitService.loadGames()
    }
}