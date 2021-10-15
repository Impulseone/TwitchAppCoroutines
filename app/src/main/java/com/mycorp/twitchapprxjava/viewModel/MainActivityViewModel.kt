package com.mycorp.twitchapprxjava.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycorp.twitchapprxjava.model.GameData
import com.mycorp.twitchapprxjava.model.TwitchResponse
import com.mycorp.twitchapprxjava.network.RetroInstance
import com.mycorp.twitchapprxjava.network.RetroService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {

    private var gamesDataList: MutableLiveData<List<GameData>> = MutableLiveData()

    fun getGamesDataListObserver() = gamesDataList

    fun makeApiCall() {
        val retroInstance  = RetroInstance.getRetroInstance().create(RetroService::class.java)
        retroInstance.loadGames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getGameDataObserver())
    }

    private fun getGameDataObserver(): Observer<TwitchResponse> {
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

    private fun parseTwitchResponseToGameData(response: TwitchResponse):List<GameData>{
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


}