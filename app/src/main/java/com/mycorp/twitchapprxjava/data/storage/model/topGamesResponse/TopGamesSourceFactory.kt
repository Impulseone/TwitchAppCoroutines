package com.mycorp.twitchapprxjava.data.storage.model.topGamesResponse

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mycorp.twitchapprxjava.data.network.retrofit.ApiService
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import io.reactivex.disposables.CompositeDisposable

class TopGamesSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val eventService: ApiService,
) : DataSource.Factory<Int, GameData>()  {

    private var mutableLiveData: MutableLiveData<TopGamesResponseSource>? = null

    init {
        mutableLiveData = MutableLiveData<TopGamesResponseSource>()
    }

    override fun create(): DataSource<Int, GameData> {
        val listDataSource = TopGamesResponseSource(eventService, compositeDisposable)
        mutableLiveData?.postValue(listDataSource)
        return listDataSource
    }
}