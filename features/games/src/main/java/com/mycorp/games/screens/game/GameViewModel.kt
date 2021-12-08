package com.mycorp.games.screens.game

import androidx.lifecycle.viewModelScope
import com.mycorp.common.Data
import com.mycorp.common.helpers.GameDataViewState
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.games.R
import com.mycorp.model.GameData
import com.mycorp.games.GameDataInfoUseCase
import com.mycorp.model.GameDataInfo
import com.mycorp.navigation.MainNavigationFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameDataInfoUseCase: GameDataInfoUseCase
) : BaseViewModel() {
    val gameLiveData = Data<GameDataViewState<GameData>>()
    val followersCountData = Data<String>()
    val favoriteResLiveData = Data<Int>()

    private var gameId: String? = null
    private val isFavoriteLiveData = Data<Boolean>()

    fun init(gameId: String) {
        this.gameId = gameId
        fetchGameData()
    }

    override fun getDataFromDb() {
        getGameData()
    }

    fun onLikeClicked() {
        isFavoriteLiveData.value?.let {
            isFavoriteLiveData.value = !it
            favoriteResLiveData.value =
                if (!it) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
            updateFavoriteData(!it)
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
                    updateLiveDataWithDataFromSource(gameDataInfoUseCase.fetchGameDataInfo(it))
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
                    updateLiveDataWithDataFromSource(gameDataInfoUseCase.getGameDataInfo(it))
                } catch (t: Throwable) {
                    handleException(t)
                }
            }
        }
    }

    private fun updateLiveDataWithDataFromSource(gameDataInfo: GameDataInfo) {
        gameLiveData.value = GameDataViewState.success(gameDataInfo.gameData)
        isFavoriteLiveData.value = gameDataInfo.isFavorite
        favoriteResLiveData.value =
            if (gameDataInfo.isFavorite) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
        followersCountData.value = gameDataInfo.followers.size.toString()
    }

    private fun updateFavoriteData(isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                gameDataInfoUseCase.insertFavorite(gameLiveData.value?.data!!)
            } else {
                gameDataInfoUseCase.deleteFavoriteById(gameId!!)
            }
        }
    }
}