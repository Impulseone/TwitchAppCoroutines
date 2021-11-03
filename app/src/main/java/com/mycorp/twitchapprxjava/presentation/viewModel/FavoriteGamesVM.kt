package com.mycorp.twitchapprxjava.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mycorp.twitchapprxjava.common.viewModel.BaseViewModel
import com.mycorp.twitchapprxjava.database.model.SingleGameData
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.GameDataViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoriteGamesVM(
    private val getFromDbUseCase: GetFromDbUseCase,
    private val disposable: CompositeDisposable
) : BaseViewModel() {

    private var gamesLiveData: MutableLiveData<GameDataViewState<PagedList<SingleGameData>>>

    init {
        gamesLiveData = MutableLiveData()
        getGames()
    }

    fun gamesLiveData() = gamesLiveData

    private fun getGames() {

        val eventPagedList = RxPagedListBuilder(getFromDbUseCase.getFavoriteGames(), 7)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
            .cache()

       disposable.add( eventPagedList
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .distinctUntilChanged()
            .doOnSubscribe {
            }.subscribe({
                gamesLiveData.postValue(
                    GameDataViewState.success(
                        data = it,
                    )
                )
            }, {
                Log.e("error", it.message.toString())
            })
       )
    }
}
