package com.mycorp.twitchapprxjava.screens.followers

import com.mycorp.twitchapprxjava.common.Data
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.models.FollowerInfo
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.models.ListItemData
import com.mycorp.twitchapprxjava.repository.FollowersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FollowersVM(
    private val followersRepository: FollowersRepository
) : BaseViewModel() {

    private var gameId: String? = null

    private var followersLiveData = Data<GameDataViewState<List<ListItemData<FollowerInfo>>>>()

    fun followersLiveData() = followersLiveData

    fun init(gameId: String) {
        this.gameId = gameId
        fetchFollowers()
    }

    override fun getDataFromDb() {
        getFollowers()
    }

    private fun fetchFollowers() {
        gameId?.let {
            followersRepository.fetchFollowers(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    followersLiveData.value =
                        GameDataViewState.success(
                            data = it.map { ListItemData(it.followerId, it) }
                        )
                }, {
                    handleException(it)
                }).addToSubscription()
        }
    }

    private fun getFollowers() {
        gameId?.let {
            followersRepository.getFollowersIdByGameId(it)
                .flatMap {
                    return@flatMap followersRepository.getFollowersByIds(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    followersLiveData.value =
                        GameDataViewState.success(
                            data = it.map { ListItemData(it.followerId, it) }
                        )
                }, {
                    handleException(it)
                }).addToSubscription()
        }
    }
}