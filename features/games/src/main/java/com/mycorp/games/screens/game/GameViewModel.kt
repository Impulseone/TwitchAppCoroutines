package com.mycorp.games.screens.game

import androidx.lifecycle.viewModelScope
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.games.GameDataInfoUseCase
import com.mycorp.model.GameData
import com.mycorp.model.GameDataInfo
import com.mycorp.navigation.MainNavigationFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameDataInfoUseCase: GameDataInfoUseCase
) : BaseViewModel() {

    val gameDataInfoFlow = MutableSharedFlow<GameDataInfo>()
    val isFavoriteFlow = MutableSharedFlow<Boolean>()

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
            gameDataInfoFlow.emit(gameDataInfo)
            isFavoriteFlow.emit(gameDataInfo.isFavorite)
        }
        isFavorite = gameDataInfo.isFavorite
    }

    private fun updateFavoriteData(isFavorite: Boolean) {
        viewModelScope.launch {
            isFavoriteFlow.emit(isFavorite)
            if (isFavorite) {
                gameData?.let { gameDataInfoUseCase.insertFavorite(it) }
            } else {
                gameId?.let { gameDataInfoUseCase.deleteFavoriteById(it) }
            }
        }
    }
}