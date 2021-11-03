package com.mycorp.twitchapprxjava.screens.games.adapter

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mycorp.twitchapprxjava.api.ApiService
import com.mycorp.twitchapprxjava.database.model.GameData
import io.reactivex.disposables.CompositeDisposable

class TopGamesSourceFactory(
    private val context: Context,
    private val apiService: ApiService
) : DataSource.Factory<Int, GameListItem>() {

    private val compositeDisposable = CompositeDisposable()

    override fun create(): DataSource<Int, GameListItem> {
        return TopGamesResponseSource(context, apiService, compositeDisposable)
    }
}