package com.mycorp.twitchapprxjava.screens.game

import androidx.annotation.DrawableRes
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.common.Data
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.models.GameData
import com.mycorp.twitchapprxjava.repository.FavoriteGamesRepository
import com.mycorp.twitchapprxjava.repository.FollowersRepository
import com.mycorp.twitchapprxjava.repository.GamesRepository
import com.mycorp.twitchapprxjava.usecases.GetGameDataUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameViewModel(
    private val followersRepository: FollowersRepository,
    private val favoriteGamesRepository: FavoriteGamesRepository,
    private val getGameDataUseCase: GetGameDataUseCase
) : BaseViewModel() {

    private var gameId: String? = null
    private val isFavoriteLiveData = Data<Boolean>()

    val gameLiveData = Data<GameDataViewState<GameData>>()
    val followersCountData = Data<String>()
    val favoriteResLiveData = Data<@DrawableRes Int>()

    fun init(gameId: String) {
        this.gameId = gameId
        getGameData()
    }

    override fun getDataFromDb() {
        getFollowers()
    }

    private fun getGameData() {
        getGameDataUseCase
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

    private fun getFollowers() {
        gameId?.let {
            followersRepository.getFollowersIdByGameId(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ list ->
                    followersCountData.value = list.size.toString()
                }, { throwable ->
                    handleException(throwable as Exception)
                }).addToSubscription()
        }
    }

    fun onLikeClicked() {
        isFavoriteLiveData.value = !isFavoriteLiveData.value!!
        favoriteResLiveData.value =
            if (isFavoriteLiveData.value!!) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
        (if (isFavoriteLiveData.value!!) {
            favoriteGamesRepository.insertFavoriteGame(gameLiveData.value?.data!!)
        } else {
            favoriteGamesRepository.deleteByGameId(gameId!!)
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {
                handleException(it)
            }).addToSubscription()
    }

    fun launchFollowerScreen() {
        navigateTo(GameFragmentDirections.actionGameFragmentToFollowersFragment(gameId!!))
    }
}