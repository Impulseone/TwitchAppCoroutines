package com.mycorp.games.screens.followers

import androidx.lifecycle.viewModelScope
import com.mycorp.common.helpers.GameDataViewState
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.model.FollowerInfo
import com.mycorp.model.ListItemData
import com.mycorp.games.GameDataInfoUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class FollowersViewModel(
    private val gameDataInfoUseCase: GameDataInfoUseCase
) : BaseViewModel() {

    private var gameId: String? = null

    val followersFlow = MutableSharedFlow<GameDataViewState<List<ListItemData<FollowerInfo>>>>()

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
                    followersFlow.emit(GameDataViewState.success(
                        data = followers.map { follower ->
                            ListItemData(follower.followerId, follower)
                        }
                    ))
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
                    followersFlow.emit(GameDataViewState.success(
                        data = followers.map { follower ->
                            ListItemData(follower.followerId, follower)
                        }
                    ))
                } catch (t: Throwable) {
                    handleException(t)
                }
            }
        }
    }
}