package com.mycorp.twitchapprxjava.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromServerUseCase
import com.mycorp.twitchapprxjava.presentation.FooBaseViewModel
import com.mycorp.twitchapprxjava.presentation.SingleLiveEvent
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GamesListViewModel(
    private val getFromServerUseCase: GetFromServerUseCase,
    private val getFromDbUseCase: GetFromDbUseCase
) : FooBaseViewModel() {

    var gamesLiveData: MutableLiveData<GameDataViewState<List<GameData>>>

    val singleLiveEvent = SingleLiveEvent<String>()

    init {
        gamesLiveData = MutableLiveData()
        getGamesFromServer()
    }

    fun getGamesDataFromServerObserver() = gamesLiveData

    private fun getGamesFromServer() {
        getFromServerUseCase.getGames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataObserver(sourceType = SourceType.SERVER))
    }

    private fun getGamesFromDb() {
        getFromDbUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataObserver(sourceType = SourceType.DATABASE))
    }

    private fun gameDataObserver(sourceType: SourceType): SingleObserver<List<GameData>> {
        return object : SingleObserver<List<GameData>> {
            override fun onSuccess(gameData: List<GameData>) {
                singleLiveEvent.value = "success"
                gamesLiveData.postValue(
                    GameDataViewState.success(
                        data = gameData,
                    )
                )
                if (sourceType == SourceType.SERVER) {
                    getFromServerUseCase.insertGames(gameData)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(insertObserver())
                }
            }

            override fun onError(e: Throwable) {
                gamesLiveData.postValue(
                    GameDataViewState.error(
                        message = e.message!!
                    )
                )
                singleLiveEvent.value = e.message!!
                if (sourceType == SourceType.SERVER) getGamesFromDb()
            }

            override fun onSubscribe(d: Disposable) {
                singleLiveEvent.value = "loading started"
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
                Log.i("insert", "insert success")
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }
    }


}