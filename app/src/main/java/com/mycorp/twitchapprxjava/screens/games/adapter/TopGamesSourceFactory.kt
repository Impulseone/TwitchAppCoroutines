package com.mycorp.twitchapprxjava.screens.games.adapter

import android.content.Context
import androidx.paging.DataSource
import com.mycorp.twitchapprxjava.api.ApiService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class TopGamesSourceFactory(
    private val context: Context,
    private val apiService: ApiService
) : DataSource.Factory<Int, GameListItem>() {

    private val compositeDisposable = CompositeDisposable()
    private val throwableStateSubject: PublishSubject<Throwable> = PublishSubject.create()

    fun getThrowableSubject() = throwableStateSubject

    override fun create(): DataSource<Int, GameListItem> {
        return TopGamesResponseSource(
            context,
            apiService,
            compositeDisposable,
            throwableStateSubject
        )
    }
}