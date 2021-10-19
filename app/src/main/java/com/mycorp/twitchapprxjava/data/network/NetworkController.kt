package com.mycorp.twitchapprxjava.data.network

import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import io.reactivex.Single

interface NetworkController {
   fun getDataFromNetwork(): Single<TwitchResponse>
}