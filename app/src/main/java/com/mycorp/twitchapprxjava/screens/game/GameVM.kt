package com.mycorp.twitchapprxjava.screens.game

import androidx.lifecycle.MutableLiveData
import com.mycorp.twitchapprxjava.common.TCommand
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import com.mycorp.twitchapprxjava.database.model.GameData
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

    private lateinit var gameId: String

    private val gameLiveData = MutableLiveData<GameDataViewState<GameData>>()
    private val followersIdLiveData = MutableLiveData<List<String>>()
    private val isFavoriteLiveData = MutableLiveData<Boolean>()
    val launchFollowerScreenCommand = TCommand<Any>()

    fun singleGameLiveData() = gameLiveData
    fun followersIdLiveData() = followersIdLiveData
    fun favoriteStateLiveData() = isFavoriteLiveData

    fun init(gameId: String) {
        this.gameId = gameId
        getGameData()
        getFollowersListFromServer()
        checkIsFavorite()
    }

    private fun getGameData() {
        gamesRepository.getGameDataById(gameId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                gameLiveData.value = GameDataViewState.success(it)
            }, {
                handleException(it)
            })
            .addToSubscription()
    }

    private fun getFollowersListFromServer() {
        followersRepository.getFollowersListFromServer(gameId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                followersIdLiveData.value = it.map {
                    it.followerId
                }
                saveFollowersToDb(it)
            }, {
                handleException(it)
                getFollowersFromDb()
            }).addToSubscription()
    }

    private fun saveFollowersToDb(followersInfo: List<FollowerInfo>) {
        followersRepository.insertFollowersToDb(followersInfo, gameId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun getFollowersFromDb() {
        followersRepository.getFollowersIdFromDbByGameId(gameId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                followersIdLiveData.value = it
            }, {
                handleException(it as Exception)
            }).addToSubscription()
    }

    private fun checkIsFavorite() {
        favoriteGamesRepository.checkIsFavorite(gameId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isFavoriteLiveData.value = it > 0
            }, {
                handleException(it)
            }).addToSubscription()
    }

    fun onLikeClicked(gameData: GameData) {
        if (isFavoriteLiveData.value!!) {
            isFavoriteLiveData.value = false
            favoriteGamesRepository.deleteByGameId(gameData.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe().addToSubscription()
        } else {
            isFavoriteLiveData.value = true
            favoriteGamesRepository.insertFavoriteGame(gameData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe().addToSubscription()
        }

    }

    fun launchFollowerScreen(gameId: String) {
        launchFollowerScreenCommand.value = gameId
    }

}