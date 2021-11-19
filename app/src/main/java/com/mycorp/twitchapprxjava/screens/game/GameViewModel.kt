package com.mycorp.twitchapprxjava.screens.game

import androidx.annotation.DrawableRes
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.common.Data
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.models.GameData
import com.mycorp.twitchapprxjava.usecases.FavoriteGamesUseCase
import com.mycorp.twitchapprxjava.usecases.GameDataUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameViewModel(
    private val gameDataUseCase: GameDataUseCase,
    private val favoriteGamesUseCase: FavoriteGamesUseCase
) : BaseViewModel() {

    private var gameId: String? = null
    private val isFavoriteLiveData = Data<Boolean>()

    val gameLiveData = Data<GameDataViewState<GameData>>()
    val followersCountData = Data<String>()
    val favoriteResLiveData = Data<@DrawableRes Int>()

    fun init(gameId: String) {
        this.gameId = gameId
        fetchGameData()
    }

    override fun getDataFromDb() {
        getGameData()
    }

    private fun fetchGameData() {
        gameDataUseCase
            .fetchGameData(gameId!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ (isFavorite, gameData, followersCount) ->
                gameLiveData.value = GameDataViewState.success(gameData)
                isFavoriteLiveData.value = isFavorite > 0
                favoriteResLiveData.value =
                    if (isFavoriteLiveData.value!!) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
                followersCountData.value = followersCount
            }, { throwable ->
                handleException(throwable)
            })
            .addToSubscription()
    }

    private fun getGameData() {
        gameDataUseCase
            .getGameData(gameId!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ (isFavorite, gameData, followersCount) ->
                gameLiveData.value = GameDataViewState.success(gameData)
                isFavoriteLiveData.value = isFavorite > 0
                favoriteResLiveData.value =
                    if (isFavoriteLiveData.value!!) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
                followersCountData.value = followersCount
            }, { throwable ->
                handleException(throwable)
            })
            .addToSubscription()
    }

    fun onLikeClicked() {
        try {
            if (isFavoriteLiveData.value != null) isFavoriteLiveData.value =
                !isFavoriteLiveData.value!!
            favoriteResLiveData.value =
                if (isFavoriteLiveData.value!!) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
            (if (isFavoriteLiveData.value!!) {
                favoriteGamesUseCase.saveGame(gameLiveData.value?.data!!)
            } else {
                favoriteGamesUseCase.deleteGameById(gameId!!)
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
        navigateTo(GameFragmentDirections.actionGameFragmentToFollowersFragment(gameId!!))
    }
}