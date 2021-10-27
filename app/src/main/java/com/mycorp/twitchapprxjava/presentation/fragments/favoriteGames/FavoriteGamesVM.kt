package com.mycorp.twitchapprxjava.presentation.fragments.favoriteGames

import androidx.lifecycle.MutableLiveData
import com.mycorp.twitchapprxjava.data.storage.model.SingleGameData
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.presentation.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.GameDataViewState
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoriteGamesVM(private val getFromDbUseCase: GetFromDbUseCase) : BaseViewModel() {
    private var gamesLiveData: MutableLiveData<GameDataViewState<List<SingleGameData>>>

    init {
        gamesLiveData = MutableLiveData()
        getGames()
    }

    fun gamesLiveData() = gamesLiveData

    private fun getGames() {
        getFromDbUseCase.getFavoriteGames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataObserver())
    }

    private fun gameDataObserver(): SingleObserver<List<SingleGameData>> {
        return object : SingleObserver<List<SingleGameData>> {
            override fun onSuccess(gameData: List<SingleGameData>) {
                gamesLiveData.postValue(
                    GameDataViewState.success(
                        data = gameData,
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

            override fun onSubscribe(d: Disposable) {
                gamesLiveData.postValue(
                    GameDataViewState.loading()
                )
            }
        }
    }

}