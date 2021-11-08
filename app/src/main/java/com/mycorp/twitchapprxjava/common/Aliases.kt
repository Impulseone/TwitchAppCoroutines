package com.mycorp.twitchapprxjava.common

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.common.helpers.SingleLiveEvent

typealias PagedDataList<T> = MutableLiveData<GameDataViewState<PagedList<T>>>
typealias TCommand<T> = SingleLiveEvent<T>