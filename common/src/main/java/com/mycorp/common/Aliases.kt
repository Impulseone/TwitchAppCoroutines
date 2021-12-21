package com.mycorp.common

import androidx.lifecycle.MutableLiveData
import com.mycorp.common.helpers.SingleLiveEvent

typealias TCommand<T> = SingleLiveEvent<T>
typealias Data<T> = MutableLiveData<T>
typealias Text<T> = MutableLiveData<T>