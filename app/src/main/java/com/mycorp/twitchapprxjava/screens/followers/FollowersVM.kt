package com.mycorp.twitchapprxjava.screens.followers

import androidx.lifecycle.MutableLiveData
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.model.FollowerInfo
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.repository.FollowersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FollowersVM(
    private val followersRepository: FollowersRepository
) : BaseViewModel() {

    private var gameId: String? = null

    private var followersLiveData: MutableLiveData<GameDataViewState<List<FollowerInfo>>> =
        MutableLiveData()

    fun followersLiveData() = followersLiveData

    fun init(gameId: String) {
        this.gameId = gameId
        fetchFollowers()
    }

    private fun fetchFollowers() {
        gameId?.let {
            followersRepository.fetchFollowers(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    followersLiveData.value =
                        GameDataViewState.success(
                            data = it,
                        )
                }, {
                    handleException(it)
                    getFollowersId()
                }).addToSubscription()
        }
    }

    private fun getFollowersId() {
        gameId?.let {
            followersRepository.getFollowersIdByGameId(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getFollowers(it)
                }, {
                    handleException(it)
                }).addToSubscription()
        }
    }

    private fun getFollowers(followersIds: List<String>) {
        followersRepository.getFollowersByIds(followersIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                followersLiveData.value =
                    GameDataViewState.success(
                        data = it,
                    )
            }, {
                handleException(it)
            }).addToSubscription()
    }
}