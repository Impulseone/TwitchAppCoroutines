package com.mycorp.twitchapprxjava.screens.games.adapter

import android.content.Context
import androidx.paging.PositionalDataSource
import com.mycorp.twitchapprxjava.models.GameData
import com.mycorp.twitchapprxjava.models.ListItemData
import com.mycorp.twitchapprxjava.repository.GamesRepository
import io.reactivex.Observable.fromIterable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.*

class TopGamesResponseSource(
    private val context: Context,
    private val compositeDisposable: CompositeDisposable,
    private val throwableStateSubject: PublishSubject<Throwable>,
    private val gamesRepository: GamesRepository
) : PositionalDataSource<ListItemData<GameListItem>>() {
    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<ListItemData<GameListItem>>
    ) {
        compositeDisposable.add(
            gamesRepository.fetchGamesDataList(
                limit = params.pageSize,
                offset = params.requestedStartPosition
            )
                .subscribe({
                    callback.onResult(
                        it.map { game ->
                            ListItemData(game.id, GameListItem(context, game))
                        },
                        DEFAULT_START_POSITION
                    )
                    saveData(it)
                }, {
                    throwableStateSubject.onNext(it)
                })
        )
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<ListItemData<GameListItem>>
    ) {
        compositeDisposable.add(
            gamesRepository.fetchGamesDataList(
                limit = params.loadSize,
                offset = params.startPosition
            ).subscribe({
                callback.onResult(
                    it.map { game ->
                        ListItemData(game.id, GameListItem(context, game))
                    }
                )
                saveData(it)
            }, {
                throwableStateSubject.onNext(it)
            })
        )
    }

    fun saveData(gameDataList:List<GameData>){
        gamesRepository.insertGamesData(gameDataList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {}).dispose()
    }

    companion object {
        private const val DEFAULT_START_POSITION = 0
    }
}