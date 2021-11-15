package com.mycorp.twitchapprxjava.screens.favoriteGames

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mycorp.twitchapprxjava.common.Data
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.database.model.FavoriteGameData
import com.mycorp.twitchapprxjava.repository.FavoriteGamesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoriteGamesVM(
    private val favoriteGamesRepository: FavoriteGamesRepository,
) : BaseViewModel() {

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(PAGED_LIST_PAGE_SIZE)
        .build()

    var gamesLiveData = Data<GameDataViewState<PagedList<FavoriteGameData>>>()

    fun init() {
        getGames()
    }

    private fun getGames() {
        val eventPagedList =
            RxPagedListBuilder(favoriteGamesRepository.getFavoriteGamesFromDb(), pagedListConfig)
                .setFetchScheduler(Schedulers.io())
                .buildObservable()
                .cache()
        eventPagedList
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .distinctUntilChanged()
            .doOnSubscribe {
            }.subscribe({
                gamesLiveData.postValue(
                    GameDataViewState.success(
                        data = it,
                    )
                )
            }, {
                Log.e("error", it.message.toString())
            }).addToSubscription()
    }

    companion object {
        private const val PAGED_LIST_PAGE_SIZE = 10
    }
}
