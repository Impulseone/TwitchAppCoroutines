package com.mycorp.twitchapprxjava.screens.favoriteGames.adapter

import androidx.paging.PositionalDataSource
import com.mycorp.twitchapprxjava.models.FavoriteGameData
import com.mycorp.twitchapprxjava.models.ListItemData
import com.mycorp.twitchapprxjava.repository.FavoriteGamesRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class FavoriteGamesSource(
    private val compositeDisposable: CompositeDisposable,
    private val throwableStateSubject: PublishSubject<Throwable>,
    private val favoriteGamesRepository: FavoriteGamesRepository
) : PositionalDataSource<ListItemData<FavoriteGameData>>() {

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<ListItemData<FavoriteGameData>>
    ) {
        compositeDisposable.add(
            favoriteGamesRepository.getFavoriteGames(
                limit = params.pageSize,
                offset = params.requestedStartPosition
            )
                .subscribe({
                    callback.onResult(
                        it,
                        DEFAULT_START_POSITION
                    )
                }, {
                    throwableStateSubject.onNext(it)
                })
        )
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<ListItemData<FavoriteGameData>>
    ) {
        compositeDisposable.add(
            favoriteGamesRepository.getFavoriteGames(
                limit = params.loadSize,
                offset = params.startPosition
            ).subscribe({
                callback.onResult(
                    it
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