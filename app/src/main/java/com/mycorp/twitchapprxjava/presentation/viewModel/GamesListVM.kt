package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromServerUseCase
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.SourceType
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GamesListVM(
    private val getFromServerUseCase: GetFromServerUseCase,
    private val getFromDbUseCase: GetFromDbUseCase
) : BaseViewModel() {

    private var gamesLiveData: MutableLiveData<GameDataViewState<List<GameData>>>

    init {
        gamesLiveData = MutableLiveData()
        getGamesFromServer()
    }

    fun gamesLiveData() = gamesLiveData

    private fun getGamesFromServer() {
        getFromServerUseCase.getGames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataObserver(sourceType = SourceType.SERVER))
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
                gamesLiveData.postValue(
                    GameDataViewState.success(
                        data = gameData,
                    )
                )
                if (sourceType == SourceType.SERVER) {
                    getFromServerUseCase.saveGamesToDb(gameData)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(insertObserver())
                }
            }

            override fun onError(e: Throwable) {
                showToast(e.message!!)
                gamesLiveData.postValue(
                    GameDataViewState.error()
                )
                handleException(e as Exception)
                if (sourceType == SourceType.SERVER) getGamesFromDb()
            }

            override fun onSubscribe(d: Disposable) {
                gamesLiveData.postValue(
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