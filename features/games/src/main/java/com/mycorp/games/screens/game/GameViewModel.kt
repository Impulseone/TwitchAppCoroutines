package com.mycorp.games.screens.game

import androidx.lifecycle.viewModelScope
import com.mycorp.common.Data
import com.mycorp.common.helpers.GameDataViewState
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.games.R
import com.mycorp.model.GameData
import com.mycorp.games.GameDataUseCase
import com.mycorp.model.FollowerInfo
import com.mycorp.navigation.MainNavigationFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameDataUseCase: GameDataUseCase
) : BaseViewModel() {
    val gameLiveData = Data<GameDataViewState<GameData>>()
    val followersCountData = Data<String>()
    val favoriteResLiveData = Data<Int>()

    private var gameId: String? = null
    private val isFavoriteLiveData = Data<Boolean>()

    fun init(gameId: String) {
        this.gameId = gameId
        fetchGameDataSuspend()
    }

    override fun getDataFromDb() {
        getGameDataSuspend()
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

    private fun fetchGameDataSuspend() {
        gameId?.let {
            viewModelScope.launch {
                try {
                    updateLiveDataWithDataFromSource(gameDataUseCase.fetchGameData(it))
                } catch (t: Throwable) {
                    handleException(t)
                }
            }
        }
    }

    private fun getGameDataSuspend() {
        gameId?.let {
            viewModelScope.launch {
                try {
                    updateLiveDataWithDataFromSource(gameDataUseCase.getGameData(it))
                } catch (t: Throwable) {
                    handleException(t)
                }
            }
        }
    }

    private fun updateLiveDataWithDataFromSource(triple: Triple<Int, GameData, List<FollowerInfo>>) {
        gameLiveData.value = GameDataViewState.success(triple.second)
        isFavoriteLiveData.value = triple.first > 0
        favoriteResLiveData.value =
            if (isFavoriteLiveData.value!!) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
        followersCountData.value = triple.third.size.toString()
    }

    private fun updateFavoriteData(isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                gameDataUseCase.insertFavorite(gameLiveData.value?.data!!)
            } else {
                gameDataUseCase.deleteFavoriteById(gameId!!)
            }
        }
    }
}