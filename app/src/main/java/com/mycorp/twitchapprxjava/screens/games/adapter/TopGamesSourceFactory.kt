package com.mycorp.twitchapprxjava.screens.games.adapter

import android.content.Context
import androidx.paging.DataSource
import com.mycorp.twitchapprxjava.repository.GamesRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class TopGamesSourceFactory(
    private val context: Context,
    private val gamesRepository: GamesRepository
) : DataSource.Factory<Int, GameListItem>() {

    private val compositeDisposable = CompositeDisposable()
    private val throwableStateSubject: PublishSubject<Throwable> = PublishSubject.create()

    fun getThrowableSubject() = throwableStateSubject

    override fun create(): DataSource<Int, GameListItem> {
        return TopGamesResponseSource(
            context,
            compositeDisposable,
            throwableStateSubject,
            gamesRepository
        )
    }
}