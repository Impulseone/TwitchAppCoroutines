package com.mycorp.twitchapprxjava.screens.followers

import androidx.lifecycle.MutableLiveData
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import com.mycorp.twitchapprxjava.database.model.SingleGameData
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.common.helpers.SourceType
import com.mycorp.twitchapprxjava.repository.FollowersRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FollowersVM(
    private val followersRepository: FollowersRepository
) : BaseViewModel() {

    private var followersLiveData: MutableLiveData<GameDataViewState<List<FollowerInfo>>> =
        MutableLiveData()

    fun followersLiveData() = followersLiveData

    fun getFollowersFromServer(singleGameData: SingleGameData) {
        followersRepository.getFollowersListFromServer(singleGameData.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(gameDataObserver(sourceType = SourceType.SERVER, singleGameData))
    }

    private fun getFollowersFromDb(singleGameData: SingleGameData) {
        followersRepository.getFollowersListFromDbByIds(singleGameData.followersIds)
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