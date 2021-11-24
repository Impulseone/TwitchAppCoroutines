package com.mycorp.twitchapprxjava.common

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.mycorp.model.ListItemData
import com.mycorp.twitchapprxjava.common.helpers.GameDataViewState
import com.mycorp.twitchapprxjava.common.helpers.SingleLiveEvent

typealias TCommand<T> = SingleLiveEvent<T>
typealias Data<T> = MutableLiveData<T>
typealias Text<T> = MutableLiveData<T>

typealias PagedListState<T> = GameDataViewState<PagedListItems<T>>
typealias PagedListItems<T> = PagedList<ListItemData<T>>