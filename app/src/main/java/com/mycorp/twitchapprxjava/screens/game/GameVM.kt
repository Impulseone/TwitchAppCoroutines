package com.mycorp.twitchapprxjava.screens.game

import androidx.annotation.DrawableRes
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.common.Data
import com.mycorp.twitchapprxjava.common.TCommand
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.models.GameData
import com.mycorp.twitchapprxjava.repository.FavoriteGamesRepository
import com.mycorp.twitchapprxjava.repository.FollowersRepository
import com.mycorp.twitchapprxjava.repository.GamesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameVM(
    private val followersRepository: FollowersRepository,
    private val gamesRepository: GamesRepository,
    private val favoriteGamesRepository: FavoriteGamesRepository
) : BaseViewModel() {

    private var gameId: String? = null
    private val isFavoriteLiveData = Data<Boolean>()

    val gameLiveData = Data<GameDataViewState<GameData>>()
    val followersIdLiveData = Data<List<String>>()
    val favoriteResLiveData = Data<@DrawableRes Int>()
    val launchFollowerScreenCommand = TCommand<String?>()

    fun init(gameId: String) {
        this.gameId = gameId
        getGameData()
        fetchFollowers()
        checkIsFavorite()
    }

    private fun getGameData() {
        gameId?.let {
            gamesRepository.getGameDataById(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    gameLiveData.value = GameDataViewState.success(it)
                }, {
                    handleException(it)
                })
                .addToSubscription()
        }
    }

    private fun fetchFollowers() {
        gameId?.let {
            followersRepository.fetchFollowers(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    followersIdLiveData.value = it.map {
                        it.followerId
                    }
                }, {
                    handleException(it)
                    getFollowers()
                }).addToSubscription()
        }
    }

    private fun getFollowers() {
        gameId?.let {
            followersRepository.getFollowersIdByGameId(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    followersIdLiveData.value = it
                }, {
                    handleException(it as Exception)
                }).addToSubscription()
        }
    }

    private fun checkIsFavorite() {
        gameId?.let {
            favoriteGamesRepository.checkIsFavorite(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isFavoriteLiveData.value = it > 0
                    favoriteResLiveData.value =
                        if (isFavoriteLiveData.value!!) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
                }, {
                    handleException(it)
                }).addToSubscription()
        }
    }

    fun onLikeClicked() {
        isFavoriteLiveData.value = !isFavoriteLiveData.value!!
        favoriteResLiveData.value = if (isFavoriteLiveData.value!!) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
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
        launchFollowerScreenCommand.value = gameId
    }

}