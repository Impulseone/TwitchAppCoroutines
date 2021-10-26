package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.data.storage.model.SingleGameData
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromServerUseCase
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.SourceType
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FollowersListVM(
    private val getFromServerUseCase: GetFromServerUseCase,
    private val getFromDbUseCase: GetFromDbUseCase
) : BaseViewModel() {

    private var followersLiveData: MutableLiveData<GameDataViewState<List<FollowerInfo>>> =
        MutableLiveData()

    fun followersLiveData() = followersLiveData

    fun getFollowersFromServer(singleGameData: SingleGameData) {
        getFromServerUseCase.getFollowersList(singleGameData.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataObserver(sourceType = SourceType.SERVER, singleGameData))
    }

    private fun getFollowersFromDb(singleGameData: SingleGameData) {
        getFromDbUseCase.getFollowersListByIds(singleGameData.followersIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataObserver(sourceType = SourceType.DATABASE, singleGameData))
    }

    private fun gameDataObserver(
        sourceType: SourceType,
        singleGameData: SingleGameData
    ): SingleObserver<List<FollowerInfo>> {
        return object : SingleObserver<List<FollowerInfo>> {
            override fun onSuccess(gameData: List<FollowerInfo>) {
                followersLiveData.postValue(
                    GameDataViewState.success(
                        data = gameData,
                    )
                )
            }

            override fun onError(e: Throwable) {
                showToast(e.message!!)
                followersLiveData.postValue(
                    GameDataViewState.error()
                )
                handleException(e as Exception)
                if (sourceType == SourceType.SERVER) getFollowersFromDb(singleGameData)
            }

            override fun onSubscribe(d: Disposable) {
                followersLiveData.postValue(
                    GameDataViewState.loading()
                )
            }
        }
    }
}