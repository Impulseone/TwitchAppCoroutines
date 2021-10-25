package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromServerUseCase
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.SourceType
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GameItemFragmentVM(
    private val getFromServerUseCase: GetFromServerUseCase,
    private val getFromDbUseCase: GetFromDbUseCase
) : BaseViewModel() {

    private var gameItemLiveData: MutableLiveData<GameDataViewState<List<FollowerInfo>>> =
        MutableLiveData()

    fun gameItemLiveData() = gameItemLiveData

    fun getFollowersListFromServer(id: String) {
        getFromServerUseCase.getFollowersList(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(followersListObserver(sourceType = SourceType.SERVER, id))
    }

    private fun followersListObserver(
        sourceType: SourceType,
        id: String
    ): SingleObserver<List<FollowerInfo>> {
        return object : SingleObserver<List<FollowerInfo>> {
            override fun onSuccess(followersList: List<FollowerInfo>) {
                showToast("get data success")
                gameItemLiveData.postValue(
                    GameDataViewState.success(
                        data = followersList,
                    )
                )
                if (sourceType == SourceType.SERVER) {
                    getFromServerUseCase.insertFollowers(followersList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(insertObserver())
                }
            }

            override fun onError(e: Throwable) {
                showToast(e.message!!)
                gameItemLiveData.postValue(
                    GameDataViewState.error()
                )
                handleException(e as Exception)
                if (sourceType == SourceType.SERVER) getFollowersFromDb(id)
            }

            override fun onSubscribe(d: Disposable) {
                gameItemLiveData.postValue(
                    GameDataViewState.loading()
                )
            }
        }
    }

    private fun getFollowersFromDb(id: String) {
        getFromDbUseCase.getFollowersData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(followersListObserver(sourceType = SourceType.DATABASE, id))
    }

    private fun insertObserver(): CompletableObserver {
        return object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}