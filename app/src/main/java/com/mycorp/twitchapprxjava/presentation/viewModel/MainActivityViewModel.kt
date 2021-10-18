package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromNetworkUseCase
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(
    private val getFromNetworkUseCase: GetFromNetworkUseCase,
    private val getFromDbUseCase: GetFromDbUseCase
) : ViewModel() {

    private var gamesDataList: MutableLiveData<List<GameData>> = MutableLiveData()

    fun getGamesDataListObserver() = gamesDataList

    fun getGamesFromServer() {
        getFromNetworkUseCase.getGames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataFromServerObserver())
    }

    fun getGamesFromDb() {
        getFromDbUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .subscribe(gameDataFromDbObserver())
    }

    private fun gameDataFromDbObserver(): Observer<List<GameData>> {
        return object : Observer<List<GameData>> {
            override fun onComplete() {
                //hide progress indicator .
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

            override fun onNext(gameData: List<GameData>) {
                gamesDataList.postValue(gameData)
            }

            override fun onSubscribe(d: Disposable) {
                //start showing progress indicator.
            }
        }
    }

    private fun gameDataFromServerObserver(): Observer<List<GameData>> {
        return object : Observer<List<GameData>> {
            override fun onComplete() {
                //hide progress indicator .
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

            override fun onNext(gamesData: List<GameData>) {
                gamesDataList.postValue(gamesData)
                getFromNetworkUseCase.insertGames(gamesData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(insertObserver())
            }

            override fun onSubscribe(d: Disposable) {
                //start showing progress indicator.
            }
        }
    }

    private fun insertObserver(): CompletableObserver {
        return object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
                print("insert success")
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }
    }


}