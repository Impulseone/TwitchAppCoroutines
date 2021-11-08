package com.mycorp.twitchapprxjava.screens.games

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mycorp.twitchapprxjava.common.PagedDataList
import com.mycorp.twitchapprxjava.common.TCommand
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.database.model.GameData
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.screens.games.adapter.GameListItem
import com.mycorp.twitchapprxjava.screens.games.adapter.TopGamesSourceFactory
import com.mycorp.twitchapprxjava.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.use_cases.GetFromServerUseCase
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GamesVM(
    private val getFromDbUseCase: GetFromDbUseCase,
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
        getFromDbUseCase.getGamesDataList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gamesFromDbObserver())
    }

    private fun gamesFromDbObserver(): SingleObserver<List<GameData>> {
        return object : SingleObserver<List<GameData>> {
            override fun onSuccess(gameData: List<GameData>) {

            }

            override fun onError(e: Throwable) {
                showToast(e.message!!)
                pagedGamesLiveData.postValue(
                    GameDataViewState.error()
                )
                handleException(e as Exception)
            }

            override fun onSubscribe(d: Disposable) {
                pagedGamesLiveData.postValue(
                    GameDataViewState.loading()
                )
            }
        }
    }

    companion object {
        private const val PAGED_LIST_PAGE_SIZE = 20
    }


}