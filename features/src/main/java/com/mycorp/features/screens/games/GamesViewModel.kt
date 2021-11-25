package com.mycorp.features.screens.games

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mycorp.common.Data
import com.mycorp.common.PagedListState
import com.mycorp.common.helpers.GameDataViewState
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.features.screens.games.adapter.DbGamesSourceFactory
import com.mycorp.features.screens.games.adapter.GameListItem
import com.mycorp.features.screens.games.adapter.TopGamesSourceFactory
import com.mycorp.features.usecases.GameDataUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GamesViewModel(
    private val topGamesSourceFactory: TopGamesSourceFactory,
    private val dbGamesSourceFactory: DbGamesSourceFactory,
) : BaseViewModel() {

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(PAGED_LIST_PAGE_SIZE)
        .build()

    val pagedGamesLiveData = Data<PagedListState<GameListItem>>()

    fun init() {
        topGamesSourceFactory.getThrowableSubject()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                handleException(it)
            }.addToSubscription()

        RxPagedListBuilder(topGamesSourceFactory, pagedListConfig)
            .buildObservable()
            .subscribe {
                pagedGamesLiveData.value = GameDataViewState.success(data = it)
            }
            .addToSubscription()
    }

    override fun getDataFromDb() {
        getGames()
    }

    fun gameItemClicked(position: Int) {
        pagedGamesLiveData.value?.data?.get(position)?.id?.let {
            navigateTo(GamesFragmentDirections.actionGamesFragmentToGameFragment(it))
        }
    }

    private fun getGames() {
        RxPagedListBuilder(dbGamesSourceFactory, pagedListConfig)
            .buildObservable()
            .subscribe({
                pagedGamesLiveData.value = GameDataViewState.success(data = it)
            }, {
                pagedGamesLiveData.postValue(
                    GameDataViewState.error()
                )
                handleException(it)
            }).addToSubscription()
    }

    companion object {
        private const val PAGED_LIST_PAGE_SIZE = 20
    }

}