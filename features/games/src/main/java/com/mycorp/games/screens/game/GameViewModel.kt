package com.mycorp.games.screens.game

import com.mycorp.common.Data
import com.mycorp.common.helpers.GameDataViewState
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.games.R
import com.mycorp.model.GameData
import com.mycorp.favorite_games.FavoriteGamesUseCase
import com.mycorp.games.usecases.GameDataUseCase
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
    val favoriteResLiveData = Data<Int>()

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
            .subscribe({ (isFavorite, gameData, followers) ->
                gameLiveData.value = GameDataViewState.success(gameData)
                isFavoriteLiveData.value = isFavorite > 0
                favoriteResLiveData.value =
                    if (isFavoriteLiveData.value!!) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
                followersCountData.value = followers.size.toString()
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
            .subscribe({ (isFavorite, gameData, followers) ->
                gameLiveData.value = GameDataViewState.success(gameData)
                isFavoriteLiveData.value = isFavorite > 0
                favoriteResLiveData.value =
                    if (isFavoriteLiveData.value!!) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
                followersCountData.value = followers.size.toString()
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