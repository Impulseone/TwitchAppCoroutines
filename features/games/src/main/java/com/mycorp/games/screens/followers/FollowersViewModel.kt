package com.mycorp.games.screens.followers

import androidx.lifecycle.viewModelScope
import com.mycorp.common.Data
import com.mycorp.common.helpers.GameDataViewState
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.model.FollowerInfo
import com.mycorp.model.ListItemData
import com.mycorp.games.GameDataUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

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
            viewModelScope.launch {
                try {
                    val followers = gameDataUseCase.fetchGameDataSuspend(it).third
                    followersLiveData.value = GameDataViewState.success(
                        data = followers.map { follower ->
                            ListItemData(follower.followerId, follower)
                        }
                    )
                } catch (t: Throwable) {
                    handleException(t)
                }
            }
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