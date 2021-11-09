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
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
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
    val launchGameScreenCommand = TCommand<Any>()

    fun init() {
        topGamesSourceFactory.getThrowableSubject()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                showToast(it.message!!)
                getGamesFromDb()
            }.addToSubscription()

        RxPagedListBuilder(topGamesSourceFactory, pagedListConfig)
            .buildObservable()
            .subscribe {
                pagedGamesLiveData.value = GameDataViewState.success(data = it)
            }
            .addToSubscription()
    }

    fun gameItemClicked(position: Int) {
        launchGameScreenCommand.value = position
    }

    private fun getGamesFromDb() {
        val dataSourceFactory = gamesRepository.getGamesDataFromDb()
        RxPagedListBuilder(dataSourceFactory, pagedListConfig)
            .buildObservable()
            .subscribe(gamesFromDbObserver())
    }

    private fun gamesFromDbObserver(): Observer<PagedList<GameListItem>> {
        return object : Observer<PagedList<GameListItem>> {

            override fun onSubscribe(d: Disposable) {
                pagedGamesLiveData.postValue(
                    GameDataViewState.loading()
                )
            }

            override fun onNext(t: PagedList<GameListItem>) {
                pagedGamesLiveData.value = GameDataViewState.success(data = t)
            }

            override fun onError(e: Throwable) {
                showToast(e.message!!)
                pagedGamesLiveData.postValue(
                    GameDataViewState.error()
                )
                handleException(e as Exception)
            }

            override fun onComplete() {

            }
        }
    }

    companion object {
        private const val PAGED_LIST_PAGE_SIZE = 20
    }


}