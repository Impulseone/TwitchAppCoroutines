package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
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

    private fun gameDataFromDbObserver(): Observer<List<GameDataTable>> {
        return object : Observer<List<GameDataTable>> {
            override fun onComplete() {
                //hide progress indicator .
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

            override fun onNext(t: List<GameDataTable>) {
                gamesDataList.postValue(parseGameDataTableToGameData(t))
            }

            override fun onSubscribe(d: Disposable) {
                //start showing progress indicator.
            }
        }
    }

    private fun gameDataFromServerObserver(): Observer<TwitchResponse> {
        return object : Observer<TwitchResponse> {
            override fun onComplete() {
                //hide progress indicator .
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

            override fun onNext(t: TwitchResponse) {
                gamesDataList.postValue(parseTwitchResponseToGameData(t))
                getFromNetworkUseCase.insertGames(parseTwitchResponseToGameDataTableList(t))
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

    private fun parseTwitchResponseToGameData(response: TwitchResponse): List<GameData> {
        val gamesData: MutableList<GameData> = mutableListOf()
        for (item in response.top!!) {
            gamesData.add(
                GameData(
                    item?.game?.id!!,
                    item.game.name!!,
                    item.game.box?.large!!,
                    item.channels!!,
                    item.viewers!!
                )
            )
        }
        return gamesData
    }

    private fun parseGameDataTableToGameData(gamesDataTables: List<GameDataTable>): List<GameData> {
        val gamesData: MutableList<GameData> = mutableListOf()
        for (item in gamesDataTables) {
            gamesData.add(
                GameData(
                    item.id,
                    item.name,
                    item.logoUrl,
                    item.channelsCount,
                    item.watchersCount
                )
            )
        }
        return gamesData
    }

    private fun parseTwitchResponseToGameDataTableList(twitchResponse: TwitchResponse): List<GameDataTable> {
        val gamesData: MutableList<GameDataTable> = mutableListOf()
        for (item in twitchResponse.top!!) {
            gamesData.add(
                GameDataTable(
                    item?.game?.id!!,
                    item.game.name!!,
                    item.game.box?.large!!,
                    item.channels!!,
                    item.viewers!!
                )
            )
        }
        return gamesData
    }


}