package com.mycorp.twitchapprxjava.screens.games

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mycorp.twitchapprxjava.common.Data
import com.mycorp.twitchapprxjava.common.PagedListState
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.repository.GamesRepository
import com.mycorp.twitchapprxjava.screens.games.adapter.GameListItem
import com.mycorp.twitchapprxjava.screens.games.adapter.TopGamesSourceFactory
import com.mycorp.twitchapprxjava.usecases.FavoriteGamesUseCase
import com.mycorp.twitchapprxjava.usecases.GameDataUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GamesViewModel(
    private val gamesUseCase: GameDataUseCase,
    private val topGamesSourceFactory: TopGamesSourceFactory,
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
        val dataSourceFactory = gamesUseCase.getGames()
        RxPagedListBuilder(dataSourceFactory, pagedListConfig)
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