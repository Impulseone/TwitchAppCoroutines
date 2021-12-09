package com.mycorp.games.screens.game

import androidx.lifecycle.viewModelScope
import com.mycorp.common.helpers.GameDataViewState
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.games.GameDataInfoUseCase
import com.mycorp.games.R
import com.mycorp.model.GameData
import com.mycorp.model.GameDataInfo
import com.mycorp.navigation.MainNavigationFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameDataInfoUseCase: GameDataInfoUseCase
) : BaseViewModel() {
    val gameFlow = MutableSharedFlow<GameDataViewState<GameData>>()
    val followersCountFlow = MutableSharedFlow<String>()
    val favoriteResFlow = MutableSharedFlow<Int>()
    private var isFavorite: Boolean? = null

    private var gameId: String? = null
    private var gameData: GameData? = null

    fun init(gameId: String) {
        this.gameId = gameId
        fetchGameData()
    }

    override fun getDataFromDb() {
        getGameData()
    }

    fun onLikeClicked() {
        isFavorite?.let {
            viewModelScope.launch {
                isFavorite = !it
                favoriteResFlow.emit(
                    if (!it) R.drawable.like_filled_icon else R.drawable.like_outlined_icon)

                updateFavoriteData(!it)
            }
        }
    }

    fun launchFollowerScreen() {
        navigateTo(
            MainNavigationFlow.FollowersFlow,
            GameFragmentDirections.actionGameFragmentToFollowersFragment(gameId!!)
        )
    }

    private fun fetchGameData() {
        gameId?.let {
            viewModelScope.launch {
                try {
                    updateFlowWithDataFromSource(gameDataInfoUseCase.fetchGameDataInfo(it))
                } catch (t: Throwable) {
                    handleException(t)
                }
            }
        }
    }

    private fun getGameData() {
        gameId?.let {
            viewModelScope.launch {
                try {
                    updateFlowWithDataFromSource(gameDataInfoUseCase.getGameDataInfo(it))
                } catch (t: Throwable) {
                    handleException(t)
                }
            }
        }
    }

    private fun updateFlowWithDataFromSource(gameDataInfo: GameDataInfo) {
        viewModelScope.launch {
            gameData = gameDataInfo.gameData
            gameFlow.emit(GameDataViewState.success(gameDataInfo.gameData))
            followersCountFlow.emit(gameDataInfo.followers.size.toString())
            favoriteResFlow.emit(
                if (gameDataInfo.isFavorite) R.drawable.like_filled_icon else R.drawable.like_outlined_icon)
        }
        isFavorite = gameDataInfo.isFavorite
    }

    private fun updateFavoriteData(isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                gameData?.let { gameDataInfoUseCase.insertFavorite(it) }
            } else {
                gameId?.let { gameDataInfoUseCase.deleteFavoriteById(it) }
            }
        }
    }
}