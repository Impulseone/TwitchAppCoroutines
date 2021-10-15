package com.mycorp.twitchapprxjava.data.network

import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import io.reactivex.Observable

interface NetworkController {
   fun getDataFromNetwork(): Observable<TwitchResponse>
}