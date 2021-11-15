package com.mycorp.twitchapprxjava.common.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.mycorp.twitchapprxjava.api.dto.topGamesResponse.ConvertDtoException
import com.mycorp.twitchapprxjava.common.helpers.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    val showToast = SingleLiveEvent<Pair<String, Int>>()

    fun showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
        showToast.value = text to length
    }

    fun handleException(e: Throwable) {
        when (e) {
            is ConvertDtoException -> run {
                Log.e("Dto", e.message.toString())
            }
            else -> {
                Log.e("Error", e.message.toString())
            }
        }
    }

    protected fun Disposable.addToSubscription() { disposables.add(this) }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        disposables.clear()
    }
}