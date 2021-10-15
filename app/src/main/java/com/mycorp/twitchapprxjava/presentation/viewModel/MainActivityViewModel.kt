package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.TwitchResponse
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromNetworkUseCase
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel (private val getFromNetworkUseCase: GetFromNetworkUseCase) : ViewModel() {

    private var gamesDataList: MutableLiveData<List<GameData>> = MutableLiveData()

    fun getGamesDataListObserver() = gamesDataList

    fun makeApiCall() {
        getFromNetworkUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataObserver())
    }

    private fun gameDataObserver(): Observer<TwitchResponse> {
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