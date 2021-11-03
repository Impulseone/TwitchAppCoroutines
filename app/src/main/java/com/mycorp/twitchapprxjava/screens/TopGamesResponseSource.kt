package com.mycorp.twitchapprxjava.screens

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.mycorp.twitchapprxjava.api.ApiService
import com.mycorp.twitchapprxjava.database.model.GameData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TopGamesResponseSource(
    private val apiService: ApiService,
    private val compositeDisposable: CompositeDisposable,

    ) : PageKeyedDataSource<Int, GameData>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GameData>
    ) {
        createObservable(1, 2, callback, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GameData>) {
        val page = params.key
        createObservable(page, page + 1, null, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GameData>) {
        val page = params.key
        createObservable(page, page - 1, null, callback)
    }

    private fun createObservable(
        requestedPage: Int,
        adjacentPage: Int,
        initialCallback: LoadInitialCallback<Int, GameData>?,
        callback: LoadCallback<Int, GameData>?
    ) {
        compositeDisposable.add(
            apiService.loadGames(requestedPage,1)
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    initialCallback?.onResult(response.toListOfGameData(), null, adjacentPage)
                    callback?.onResult(response.toListOfGameData(), adjacentPage)
                }, { error ->
                    Log.e("$error", "Fail on fetching page of events")
                }
                ))
    }
}