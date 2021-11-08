package com.mycorp.twitchapprxjava.screens.games.adapter

import android.content.Context
import android.util.Log
import androidx.paging.PositionalDataSource
import com.mycorp.twitchapprxjava.repository.GamesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class TopGamesResponseSource(
    private val context: Context,
    private val compositeDisposable: CompositeDisposable,
    private val throwableStateSubject: PublishSubject<Throwable>,
    private val gamesRepository: GamesRepository
) : PositionalDataSource<GameListItem>() {
    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<GameListItem>
    ) {
        compositeDisposable.add(
            gamesRepository.getGamesDataListFromServer(
                limit = params.pageSize,
                offset = params.requestedStartPosition
            )
                .subscribe({
                    callback.onResult(
                        it.map { game ->
                            GameListItem(context, game)
                        },
                        DEFAULT_START_POSITION
                    )
                    gamesRepository.insertGamesDataToDb(it)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                }, {
                    throwableStateSubject.onNext(it)
                })
        )
    }



    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<GameListItem>) {
        compositeDisposable.add(
            gamesRepository.getGamesDataListFromServer(
                limit = params.loadSize,
                offset = params.startPosition
            ).subscribe({
                callback.onResult(
                    it.map { game ->
                        GameListItem(context, game)
                    }
                )
                gamesRepository.insertGamesDataToDb(it)
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