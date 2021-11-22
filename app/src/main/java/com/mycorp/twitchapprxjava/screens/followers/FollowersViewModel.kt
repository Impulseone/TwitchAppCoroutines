package com.mycorp.twitchapprxjava.screens.followers

import com.mycorp.twitchapprxjava.common.Data
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.models.FollowerInfo
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.models.ListItemData
import com.mycorp.twitchapprxjava.usecases.GameDataUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FollowersViewModel(
    private val gameDataUseCase: GameDataUseCase
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
            gameDataUseCase.fetchGameData(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (_, _, followers) ->
                    followersLiveData.value = GameDataViewState.success(
                        data = followers.map { follower ->
                            ListItemData(follower.followerId, follower)
                        }
                    )
                }, { t ->
                    handleException(t)
                }).addToSubscription()
        }
    }

    private fun getFollowers() {
        gameId?.let {
            gameDataUseCase.getGameData(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (_, _, followers) ->
                    followersLiveData.value = GameDataViewState.success(
                        data = followers.map { follower ->
                            ListItemData(follower.followerId, follower)
                        }
                    )
                }, { t ->
                    handleException(t)
                }).addToSubscription()
        }
    }
}