package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.database.model.GameData
import com.mycorp.twitchapprxjava.database.model.topGamesResponse.TopGamesSourceFactory
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromServerUseCase
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.SourceType
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GamesListVM(
    private val getFromServerUseCase: GetFromServerUseCase,
    private val getFromDbUseCase: GetFromDbUseCase,
    private val topGamesSourceFactory: TopGamesSourceFactory,
    private val disposable: CompositeDisposable
) : BaseViewModel() {

    private var pagedGamesLiveData: MutableLiveData<GameDataViewState<PagedList<GameData>>>

    init {
        pagedGamesLiveData = MutableLiveData()
        initPaging()
    }

    fun gamesLiveData() = pagedGamesLiveData

    private fun initPaging() {
        val eventPagedList = RxPagedListBuilder(topGamesSourceFactory, 20)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
            .cache()

        disposable.add(eventPagedList
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .distinctUntilChanged()
            .doOnSubscribe {
            }.subscribe({
                pagedGamesLiveData.postValue(GameDataViewState.success(
                    data = it,
                ))
            }, {
                showToast(it.message!!)
                getGamesFromDb()
            })
        )
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


}