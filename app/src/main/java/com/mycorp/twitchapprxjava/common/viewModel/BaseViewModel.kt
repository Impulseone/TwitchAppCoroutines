package com.mycorp.twitchapprxjava.common.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.mycorp.twitchapprxjava.api.dto.ConvertDtoException
import com.mycorp.twitchapprxjava.common.TCommand
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    val showToast = TCommand<Pair<String, Int>>()
    val openFragmentCommand = TCommand<Any>()

    fun showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
        showToast.value = text to length
    }

    fun handleException(t: Throwable) {
        when (t) {
            is ConvertDtoException -> run {
                Log.e("Dto", t.message.toString())
            }
            is UnknownHostException -> run {
                Log.e("UnknownHostException", t.message.toString())
                showToast("Подключение к интернету отсутствует")
                getDataFromDb()
            }
            else -> {
                Log.e("Error", t.message.toString())
            }
        }
    }

    open fun getDataFromDb(){}

    protected fun Disposable.addToSubscription() { disposables.add(this) }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        disposables.clear()
    }
}