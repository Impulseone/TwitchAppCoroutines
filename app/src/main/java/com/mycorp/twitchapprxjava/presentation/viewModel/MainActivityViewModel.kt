package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromNetworkUseCase
import io.reactivex.FlowableSubscriber
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription

class MainActivityViewModel(
    private val getFromNetworkUseCase: GetFromNetworkUseCase,
    private val getFromDbUseCase: GetFromDbUseCase
) : ViewModel() {

    private var gamesDataList: MutableLiveData<List<GameData>> = MutableLiveData()

    fun getGamesDataListObserver() = gamesDataList

    fun getGamesFromServer() {
        getFromNetworkUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataFromServerObserver())
    }

    fun getGamesFromDb() {
        getFromDbUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataFromDbObserver())
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
            }

            override fun onSubscribe(d: Disposable) {
                //start showing progress indicator.
            }
        }
    }

    private fun gameDataFromDbObserver(): FlowableSubscriber<List<GameDataTable>> {
        return object : FlowableSubscriber<List<GameDataTable>> {
            override fun onSubscribe(s: Subscription) {
                //start showing progress indicator.
            }

            override fun onNext(t: List<GameDataTable>?) {
                gamesDataList.postValue(parseGameDataTableToGameData(t!!))
            }

            override fun onError(t: Throwable?) {
            }

            override fun onComplete() {
                //hide progress indicator.
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


}