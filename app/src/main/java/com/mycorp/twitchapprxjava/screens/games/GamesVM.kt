package com.mycorp.twitchapprxjava.screens.games

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mycorp.twitchapprxjava.common.PagedDataList
import com.mycorp.twitchapprxjava.common.TCommand
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.database.model.GameData
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.SourceType
import com.mycorp.twitchapprxjava.screens.games.adapter.GameListItem
import com.mycorp.twitchapprxjava.screens.games.adapter.TopGamesSourceFactory
import com.mycorp.twitchapprxjava.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.use_cases.GetFromServerUseCase
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class GamesVM(
    private val getFromServerUseCase: GetFromServerUseCase,
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
        RxPagedListBuilder(topGamesSourceFactory, pagedListConfig)
            .buildObservable()
            .subscribe({
                pagedGamesLiveData.value = GameDataViewState.success(data = it)
            }, {
                when (it) {
                    is UnknownHostException -> {
                        getGamesFromDb()
                    }
                    else -> handleException(it)
                }
            })
            .addToSubscription()
    }

    fun gameItemClicked(position: Int) {
        //testToast
        showToast("Game item clicked")
        launchGameScreenCommand.value = position
    }

    private fun getGamesFromDb() {
        getFromDbUseCase.getGamesData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataObserver(sourceType = SourceType.DATABASE))
    }

    private fun gameDataObserver(sourceType: SourceType): SingleObserver<List<GameData>> {
        return object : SingleObserver<List<GameData>> {
            override fun onSuccess(gameData: List<GameData>) {
                if (sourceType == SourceType.SERVER) {
                    getFromServerUseCase.saveGamesToDb(gameData)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(insertObserver())
                }
            }

            override fun onError(e: Throwable) {
                showToast(e.message!!)
                pagedGamesLiveData.postValue(
                    GameDataViewState.error()
                )
                handleException(e as Exception)
                if (sourceType == SourceType.SERVER) getGamesFromDb()
            }

            override fun onSubscribe(d: Disposable) {
                pagedGamesLiveData.postValue(
                    GameDataViewState.loading()
                )
            }
        }
    }

    private fun insertObserver(): CompletableObserver {
        return object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val PAGED_LIST_PAGE_SIZE = 20
    }


}