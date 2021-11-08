package com.mycorp.twitchapprxjava.screens.game

import androidx.lifecycle.MutableLiveData
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import com.mycorp.twitchapprxjava.database.model.SingleGameData
import com.mycorp.twitchapprxjava.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.use_cases.GetFromServerUseCase
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.database.model.GameData
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SingleGameDataVM(
    private val getFromServerUseCase: GetFromServerUseCase,
    private val getFromDbUseCase: GetFromDbUseCase,
) : BaseViewModel() {

    private var singleGameLiveData: MutableLiveData<GameDataViewState<SingleGameData>> =
        MutableLiveData()

    fun singleGameLiveData() = singleGameLiveData

    fun getGameById(gameId: String) {
        getFromDbUseCase.getGameDataById(gameId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataObserver())
    }

    fun getFollowersListFromServer(gameData: GameData) {
        getFromServerUseCase.getFollowersList(gameData.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(followersListObserver(gameData))
    }

    fun updateSingleGameData(singleGameData: SingleGameData) {
        getFromServerUseCase.saveSingleGameDataToDb(singleGameData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(insertObserver(singleGameData))
    }

    private fun gameDataObserver(): SingleObserver<GameData> {
        return object : SingleObserver<GameData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onSuccess(t: GameData) {
                getFollowersListFromServer(t)
            }

            override fun onError(e: Throwable) {
                showToast(e.message!!)
                e.printStackTrace()
            }

        }
    }

    private fun followersListObserver(
        gameData: GameData
    ): SingleObserver<List<FollowerInfo>> {
        return object : SingleObserver<List<FollowerInfo>> {

            override fun onSuccess(followersList: List<FollowerInfo>) {

                val singleGameData = SingleGameData.fromGameData(
                    gameData,
                    followersList
                )

                getFromServerUseCase.saveFollowersToDb(followersList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(insertObserver(null))

                getFromDbUseCase.getSingleGameData(gameData.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(singleGameDataFromDbObserver(singleGameData))

            }

            override fun onError(e: Throwable) {
                handleException(e as Exception)
                showToast(e.message!!)
                getFromDbUseCase.getSingleGameData(gameData.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(singleGameDataFromDbObserver(null))
            }

            override fun onSubscribe(d: Disposable) {
                singleGameLiveData.postValue(
                    GameDataViewState.loading()
                )
            }
        }
    }

    private fun singleGameDataFromDbObserver(singleGameDataFromServer: SingleGameData?): SingleObserver<SingleGameData> {
        return object : SingleObserver<SingleGameData> {
            override fun onSubscribe(d: Disposable) {
                singleGameLiveData.postValue(
                    GameDataViewState.loading()
                )
            }

            override fun onSuccess(singleGameDataFromDb: SingleGameData) {
                if (singleGameDataFromServer != null) {
                    val singleGameData = SingleGameData(
                        id = singleGameDataFromServer.id,
                        name = singleGameDataFromServer.name,
                        logoUrl = singleGameDataFromServer.logoUrl,
                        followersIds = singleGameDataFromServer.followersIds,
                        isLiked = singleGameDataFromDb.isLiked
                    )
                    singleGameLiveData.postValue(GameDataViewState.success(singleGameData))
                    getFromServerUseCase.saveSingleGameDataToDb(singleGameData)
                } else {
                    singleGameLiveData.postValue(GameDataViewState.success(singleGameDataFromDb))
                }
            }

            override fun onError(e: Throwable) {
                handleException(e as Exception)
                showToast(e.message!!)
                if (singleGameDataFromServer != null) {
                    singleGameLiveData.postValue(
                        GameDataViewState.success(singleGameDataFromServer)
                    )
                    getFromServerUseCase.saveSingleGameDataToDb(singleGameDataFromServer)
                } else
                    singleGameLiveData.postValue(
                        GameDataViewState.error()
                    )
            }

        }
    }

    private fun insertObserver(singleGameData: SingleGameData?): CompletableObserver {
        return object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
                if (singleGameData != null) singleGameLiveData.postValue(
                    GameDataViewState(
                        false,
                        singleGameData
                    )
                )
            }

            override fun onError(e: Throwable) {
                showToast(e.message!!)
                e.printStackTrace()
            }
        }
    }
}