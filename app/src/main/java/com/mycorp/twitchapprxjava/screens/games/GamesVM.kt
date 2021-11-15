package com.mycorp.twitchapprxjava.screens.games

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mycorp.twitchapprxjava.common.PagedDataList
import com.mycorp.twitchapprxjava.common.TCommand
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.repository.GamesRepository
import com.mycorp.twitchapprxjava.screens.games.adapter.GameListItem
import com.mycorp.twitchapprxjava.screens.games.adapter.TopGamesSourceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GamesVM(
    private val gamesRepository: GamesRepository,
    private val topGamesSourceFactory: TopGamesSourceFactory,
) : BaseViewModel() {

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(PAGED_LIST_PAGE_SIZE)
        .build()

    val pagedGamesLiveData = PagedDataList<GameListItem>()
    val launchGameScreenCommand = TCommand<String?>()

    fun init() {
        topGamesSourceFactory.getThrowableSubject()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                showToast(it.message!!)
                getGames()
            }.addToSubscription()

        RxPagedListBuilder(topGamesSourceFactory, pagedListConfig)
            .buildObservable()
            .subscribe {
                pagedGamesLiveData.value = GameDataViewState.success(data = it)
            }
            .addToSubscription()
    }

    fun gameItemClicked(position: Int) {
        val id = pagedGamesLiveData.value?.data?.get(position)?.id
        launchGameScreenCommand.value = id
    }

    private fun getGames() {
        val dataSourceFactory = gamesRepository.getGamesData()
        RxPagedListBuilder(dataSourceFactory, pagedListConfig)
            .buildObservable()
            .subscribe({
                pagedGamesLiveData.value = GameDataViewState.success(data = it)
            }, {
                pagedGamesLiveData.postValue(
                    GameDataViewState.error()
                )
                handleException(it as Exception)
            }).addToSubscription()
    }

    companion object {
        private const val PAGED_LIST_PAGE_SIZE = 20
    }

}