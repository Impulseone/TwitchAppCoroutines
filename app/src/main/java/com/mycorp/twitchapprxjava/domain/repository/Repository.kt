package com.mycorp.twitchapprxjava.domain.repository

import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import io.reactivex.Observable

interface Repository {
    fun getGamesDataFromNetwork(): Observable<TwitchResponse>
}