package com.mycorp.games.screens.followers

import androidx.lifecycle.viewModelScope
import com.mycorp.common.Data
import com.mycorp.common.helpers.GameDataViewState
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.model.FollowerInfo
import com.mycorp.model.ListItemData
import com.mycorp.games.GameDataInfoUseCase
import kotlinx.coroutines.launch

class FollowersViewModel(
    private val gameDataInfoUseCase: GameDataInfoUseCase
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
                    val followers = gameDataInfoUseCase.fetchGameDataInfo(it).followers
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
            viewModelScope.launch {
                try {
                    val followers = gameDataInfoUseCase.getGameDataInfo(it).followers
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
}