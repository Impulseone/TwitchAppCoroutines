package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromServerUseCase
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
            .subscribe(followersListObserver(sourceType = SourceType.SERVER))
    }

    private fun followersListObserver(sourceType: SourceType): SingleObserver<List<FollowerInfo>> {
        return object : SingleObserver<List<FollowerInfo>> {
            override fun onSuccess(followersList: List<FollowerInfo>) {
                showToast("get data success")
                gameItemLiveData.postValue(
                    GameDataViewState.success(
                        data = followersList,
                    )
                )
            }

            override fun onError(e: Throwable) {
                showToast(e.message!!)
                gameItemLiveData.postValue(
                    GameDataViewState.error()
                )
                handleException(e as Exception)
            }

            override fun onSubscribe(d: Disposable) {
                gameItemLiveData.postValue(
                    GameDataViewState.loading()
                )
            }
        }
    }
}