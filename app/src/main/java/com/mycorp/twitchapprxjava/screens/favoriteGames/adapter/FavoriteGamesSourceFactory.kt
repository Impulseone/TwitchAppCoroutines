package com.mycorp.twitchapprxjava.screens.favoriteGames.adapter

import androidx.paging.DataSource
import com.mycorp.model.FavoriteGameData
import com.mycorp.model.ListItemData
import com.mycorp.twitchapprxjava.repository.FavoriteGamesRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class FavoriteGamesSourceFactory(private val favoriteGamesRepository: FavoriteGamesRepository) :
    DataSource.Factory<Int, ListItemData<FavoriteGameData>>() {
    private val compositeDisposable = CompositeDisposable()
    private val throwableStateSubject: PublishSubject<Throwable> = PublishSubject.create()

    fun getThrowableSubject() = throwableStateSubject

    override fun create(): DataSource<Int, ListItemData<FavoriteGameData>> {
        return FavoriteGamesSource(
            compositeDisposable,
            throwableStateSubject,
            favoriteGamesRepository
        )
    }

}