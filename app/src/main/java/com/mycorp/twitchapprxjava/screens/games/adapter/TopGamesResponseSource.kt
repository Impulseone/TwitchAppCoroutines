package com.mycorp.twitchapprxjava.screens.games.adapter

import android.content.Context
import android.util.Log
import androidx.paging.PositionalDataSource
import com.mycorp.twitchapprxjava.api.ApiService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class TopGamesResponseSource(
    private val context: Context,
    private val apiService: ApiService,
    private val compositeDisposable: CompositeDisposable,
    private val throwableStateSubject: PublishSubject<Throwable>
) : PositionalDataSource<GameListItem>() {
    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<GameListItem>
    ) {
        compositeDisposable.add(
            apiService.loadGames(
                limit = params.pageSize,
                offset = params.requestedStartPosition
            ).subscribe({
                callback.onResult(
                    it.toListOfGameData().map { game ->
                        GameListItem(context, game)
                    },
                    DEFAULT_START_POSITION
                )
            }, {
                throwableStateSubject.onNext(it)
            })
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<GameListItem>) {
        compositeDisposable.add(
            apiService.loadGames(
                limit = params.loadSize,
                offset = params.startPosition
            ).subscribe({
                callback.onResult(
                    it.toListOfGameData().map { game ->
                        GameListItem(context, game)
                    }
                )
            }, {
                Log.d(RESPONSE_SOURCE_TAG, "$it")
            })
        )
    }

    companion object {
        private const val RESPONSE_SOURCE_TAG = "RESPONSE_SOURCE_TAG"
        private const val DEFAULT_START_POSITION = 0
    }
}