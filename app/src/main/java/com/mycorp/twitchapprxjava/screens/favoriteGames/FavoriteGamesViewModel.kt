package com.mycorp.twitchapprxjava.screens.favoriteGames

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mycorp.model.FavoriteGameData
import com.mycorp.twitchapprxjava.common.Data
import com.mycorp.twitchapprxjava.common.PagedListState
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.screens.favoriteGames.adapter.FavoriteGamesSourceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoriteGamesViewModel(
    private val favoriteGamesSourceFactory: FavoriteGamesSourceFactory
) : BaseViewModel() {

    var gamesLiveData = Data<PagedListState<FavoriteGameData>>()

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(PAGED_LIST_PAGE_SIZE)
        .build()

    fun init() {
        favoriteGamesSourceFactory.getThrowableSubject()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                handleException(it)
            }.addToSubscription()

        RxPagedListBuilder(favoriteGamesSourceFactory, pagedListConfig)
            .buildObservable()
            .subscribe {
                gamesLiveData.value = GameDataViewState.success(data = it)
            }
            .addToSubscription()
    }

    companion object {
        private const val PAGED_LIST_PAGE_SIZE = 10
    }
}
