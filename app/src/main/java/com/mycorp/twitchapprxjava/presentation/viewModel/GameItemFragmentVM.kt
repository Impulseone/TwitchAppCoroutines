package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameItemData
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromServerUseCase
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.SourceType
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GameItemFragmentVM(
    private val getFromServerUseCase: GetFromServerUseCase,
    private val getFromDbUseCase: GetFromDbUseCase
) : BaseViewModel() {

    private var gameItemLiveData: MutableLiveData<GameDataViewState<GameItemData>> =
        MutableLiveData()

    fun gameItemLiveData() = gameItemLiveData

    fun getFollowersListFromServer(gameData: GameData) {
        getFromServerUseCase.getFollowersList(gameData.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(followersListObserver(sourceType = SourceType.SERVER, gameData))
    }

    private fun followersListObserver(
        sourceType: SourceType,
        gameData: GameData
    ): SingleObserver<List<FollowerInfo>> {
        return object : SingleObserver<List<FollowerInfo>> {
            override fun onSuccess(followersList: List<FollowerInfo>) {
                gameItemLiveData.postValue(
                    GameDataViewState.success(
                        data = GameItemData.fromGameData(
                            gameData,
                            followersList
                        )
                    )
                )
                if (sourceType == SourceType.SERVER) {
                    getFromServerUseCase.insertFollowers(followersList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(insertObserver())
                }
            }

            override fun onError(e: Throwable) {
                handleException(e as Exception)
                showToast(e.message!!)
                gameItemLiveData.postValue(
                    GameDataViewState.error()
                )
                if (sourceType == SourceType.SERVER) getFollowersFromDb(gameData)
            }

            override fun onSubscribe(d: Disposable) {
                gameItemLiveData.postValue(
                    GameDataViewState.loading()
                )
            }
        }
    }

    private fun getFollowersFromDb(gameData: GameData) {
        getFromDbUseCase.getFollowersData(gameData.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(followersListObserver(sourceType = SourceType.DATABASE, gameData))
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