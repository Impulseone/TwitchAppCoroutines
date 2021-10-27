package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.SingleGameData
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromServerUseCase
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.GameDataViewState
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SingleGameDataFragmentVM(
    private val getFromServerUseCase: GetFromServerUseCase,
    private val getFromDbUseCase: GetFromDbUseCase,
) : BaseViewModel() {

    private var singleGameLiveData: MutableLiveData<GameDataViewState<SingleGameData>> =
        MutableLiveData()

    fun gameItemLiveData() = singleGameLiveData

    fun getFollowersListFromServer(gameData: GameData) {
        getFromServerUseCase.getFollowersList(gameData.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(followersListObserver(gameData))
    }

    fun updateSingleGameData(singleGameData: SingleGameData){
        getFromServerUseCase.saveSingleGameDataToDb(singleGameData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(insertObserver(singleGameData))
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
                    .subscribe(insertObserver(singleGameData))

                getFromServerUseCase.saveSingleGameDataToDb(singleGameData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(insertObserver(singleGameData))

                singleGameLiveData.postValue(GameDataViewState.success(singleGameData))
            }

            override fun onError(e: Throwable) {
                handleException(e as Exception)
                showToast(e.message!!)
                singleGameLiveData.postValue(
                    GameDataViewState.error()
                )
                getSingleGameDataFromDb(gameData)
            }

            override fun onSubscribe(d: Disposable) {
                singleGameLiveData.postValue(
                    GameDataViewState.loading()
                )
            }
        }
    }

    private fun gameItemDataFromDbObserver(): SingleObserver<SingleGameData> {
        return object : SingleObserver<SingleGameData> {
            override fun onSubscribe(d: Disposable) {
                singleGameLiveData.postValue(
                    GameDataViewState.loading()
                )
            }

            override fun onSuccess(t: SingleGameData) {
                singleGameLiveData.postValue(GameDataViewState.success(t))
            }

            override fun onError(e: Throwable) {
                handleException(e as Exception)
                showToast(e.message!!)
                singleGameLiveData.postValue(
                    GameDataViewState.error()
                )
            }

        }
    }

    private fun getSingleGameDataFromDb(gameData: GameData) {
        getFromDbUseCase.getSingleGameData(gameData.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameItemDataFromDbObserver())
    }

    private fun insertObserver(singleGameData: SingleGameData): CompletableObserver {
        return object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
                singleGameLiveData.postValue(GameDataViewState(false,singleGameData))
            }

            override fun onError(e: Throwable) {
                showToast(e.message!!)
                e.printStackTrace()
            }
        }
    }
}