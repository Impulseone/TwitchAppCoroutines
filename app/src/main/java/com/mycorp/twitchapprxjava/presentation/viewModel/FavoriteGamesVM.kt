package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.mycorp.twitchapprxjava.data.storage.model.SingleGameData
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.GameDataViewState
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoriteGamesVM(private val getFromDbUseCase: GetFromDbUseCase) : BaseViewModel() {

    private var gamesLiveData: MutableLiveData<GameDataViewState<PagingData<SingleGameData>>>

    init {
        gamesLiveData = MutableLiveData()
        getGames()
    }

    fun gamesLiveData() = gamesLiveData

    private fun getGames() {
        getFromDbUseCase.getPagedFavoriteGames().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataObserver())
    }

    private fun gameDataObserver(): Observer<PagingData<SingleGameData>> {
        return object : Observer<PagingData<SingleGameData>> {
            override fun onSubscribe(d: Disposable) {
                gamesLiveData.postValue(
                    GameDataViewState.loading()
                )
            }

            override fun onNext(t: PagingData<SingleGameData>) {
                gamesLiveData.postValue(
                    GameDataViewState.success(
                        data = t,
                    )
                )
            }

            override fun onError(e: Throwable) {
                showToast(e.message!!)
                gamesLiveData.postValue(
                    GameDataViewState.error()
                )
                handleException(e as Exception)
            }

            override fun onComplete() {
            }

        }
    }
}
