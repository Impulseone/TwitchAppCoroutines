package com.mycorp.twitchapprxjava.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromServerUseCase
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(
    private val getFromServerUseCase: GetFromServerUseCase,
    private val getFromDbUseCase: GetFromDbUseCase
) : ViewModel() {

    private var gamesFromServerLiveData: MutableLiveData<Resource<List<GameData>>> =
        MutableLiveData()
    private var gamesFromDbLiveData: MutableLiveData<Resource<List<GameData>>> = MutableLiveData()

    fun getGamesDataFromServerObserver() = gamesFromServerLiveData
    fun getGamesDataFromDbObserver() = gamesFromDbLiveData

    fun getGamesFromServer() {
        getFromServerUseCase.getGames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataObserver(sourceType = SourceType.SERVER))
    }

    fun getGamesFromDb() {
        getFromDbUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .subscribe(gameDataObserver(sourceType = SourceType.DATABASE))
    }

    private fun gameDataObserver(sourceType: SourceType): Observer<List<GameData>> {
        var liveData: MutableLiveData<Resource<List<GameData>>> =
            if (sourceType == SourceType.SERVER) gamesFromServerLiveData else gamesFromDbLiveData
        return object : Observer<List<GameData>> {
            override fun onComplete() {

            }

            override fun onError(e: Throwable) {
                liveData.postValue(
                    Resource.error(
                        message = e.message!!
                    )
                )
            }

            override fun onNext(gameData: List<GameData>) {
                liveData.postValue(
                    Resource.success(
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

            override fun onSubscribe(d: Disposable) {
                liveData.postValue(
                    Resource.loading()
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