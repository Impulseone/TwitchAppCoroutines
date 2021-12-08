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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameDataUseCase: GameDataUseCase
) : BaseViewModel() {

    private var gameId: String? = null
    private val isFavoriteLiveData = Data<Boolean>()

    val gameLiveData = Data<GameDataViewState<GameData>>()
    val followersCountData = Data<String>()
    val favoriteResLiveData = Data<Int>()

    fun init(gameId: String) {
        this.gameId = gameId
        fetchGameDataSuspend()
    }

    override fun getDataFromDb() {
        getGameDataSuspend()
    }

    private fun fetchGameDataSuspend() {
        gameId?.let {
            viewModelScope.launch {
                try {
                    updateLiveDataWithDataFromSource(gameDataUseCase.fetchGameDataSuspend(it))
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
                    updateLiveDataWithDataFromSource(gameDataUseCase.getGameDataSuspend(it))
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

    fun onLikeClicked() {
        try {
            if (isFavoriteLiveData.value != null) isFavoriteLiveData.value =
                !isFavoriteLiveData.value!!
            favoriteResLiveData.value =
                if (isFavoriteLiveData.value!!) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
            (if (isFavoriteLiveData.value!!) {
                gameDataUseCase.insertFavorite(gameLiveData.value?.data!!)
            } else {
                gameDataUseCase.deleteFavoriteById(gameId!!)
            }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {
                    handleException(it)
                }).addToSubscription()
        } catch (t: Throwable) {
            handleException(t)
        }
    }

    fun launchFollowerScreen() {
        navigateTo(
            MainNavigationFlow.FollowersFlow,
            GameFragmentDirections.actionGameFragmentToFollowersFragment(gameId!!)
        )
    }
}