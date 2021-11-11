package com.mycorp.twitchapprxjava.screens.followers

import androidx.lifecycle.MutableLiveData
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.common.helpers.SourceType
import com.mycorp.twitchapprxjava.repository.FollowersRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FollowersVM(
    private val followersRepository: FollowersRepository
) : BaseViewModel() {

    private lateinit var gameId: String

    private var followersLiveData: MutableLiveData<GameDataViewState<List<FollowerInfo>>> =
        MutableLiveData()

    fun followersLiveData() = followersLiveData

    fun init(gameId: String) {
        this.gameId = gameId
        getFollowersFromServer()
    }

    private fun getFollowersFromServer() {
        followersRepository.getFollowersListFromServer(gameId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                followersLiveData.value =
                    GameDataViewState.success(
                        data = it,
                    )
            }, {
                handleException(it)
                getFollowersIdFromDb()
            }).addToSubscription()
    }

    private fun getFollowersIdFromDb() {
        followersRepository.getFollowersIdFromDbByGameId(gameId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getFollowersFromDb(it)
            }, {
                handleException(it)
            }).addToSubscription()
    }

    private fun getFollowersFromDb(followersIds: List<String>) {
        followersRepository.getFollowersListFromDbByIds(followersIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                followersLiveData.value =
                    GameDataViewState.success(
                        data = it,
                    )
            }, {
                handleException(it)
            }).addToSubscription()
    }
}