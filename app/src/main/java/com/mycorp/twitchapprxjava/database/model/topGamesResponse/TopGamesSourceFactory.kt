package com.mycorp.twitchapprxjava.database.model.topGamesResponse

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mycorp.twitchapprxjava.api.ApiService
import com.mycorp.twitchapprxjava.database.model.GameData
import com.mycorp.twitchapprxjava.screens.TopGamesResponseSource
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