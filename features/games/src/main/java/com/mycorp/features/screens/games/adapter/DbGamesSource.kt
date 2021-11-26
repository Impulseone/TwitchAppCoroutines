package com.mycorp.features.screens.games.adapter

import android.content.Context
import androidx.paging.PositionalDataSource
import com.mycorp.model.FavoriteGameData
import com.mycorp.model.GameData
import com.mycorp.model.ListItemData
import com.mycorp.myapplication.FavoriteGamesRepository
import com.mycorp.myapplication.GamesRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class DbGamesSource(
    private val compositeDisposable: CompositeDisposable,
    private val throwableStateSubject: PublishSubject<Throwable>,
    private val gamesRepository: GamesRepository,
    private val context: Context
) : PositionalDataSource<ListItemData<GameListItem>>() {

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<ListItemData<GameListItem>>
    ) {
        compositeDisposable.add(
            gamesRepository.getGamesLimited(
                limit = params.pageSize,
                offset = params.requestedStartPosition
            )
                .subscribe({
                    callback.onResult(
                        it.map { gameData ->
                            ListItemData(gameData.id, GameListItem(context, gameData))
                        },
                        DEFAULT_START_POSITION
                    )
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
            gamesRepository.getGamesLimited(
                limit = params.loadSize,
                offset = params.startPosition
            ).subscribe({
                callback.onResult(
                    it.map { gameData ->
                        ListItemData(gameData.id, GameListItem(context, gameData))
                    },
                )
            }, {
                throwableStateSubject.onNext(it)
            })
        )
    }

    companion object {
        private const val DEFAULT_START_POSITION = 0
    }
}