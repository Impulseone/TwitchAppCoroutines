package com.mycorp.common.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.mycorp.api.dto.ConvertDtoException
import com.mycorp.common.TCommand
import com.mycorp.common.helpers.TAG
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    val showToast = TCommand<Pair<String, Int>>()
    val openScreenCommand = TCommand<NavDirections>()
    val connectionExceptionCommand = TCommand<Boolean>()

    fun showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
        showToast.value = text to length
    }

    fun navigateTo(directions: NavDirections) {
        openScreenCommand.value = directions
    }

    fun handleException(t: Throwable) {
        when (t) {
            is ConvertDtoException -> run {
                Log.e(TAG, t.message.toString())
            }
            is UnknownHostException -> run {
                Log.e(TAG, t.message.toString())
                connectionExceptionCommand.value = true
                getDataFromDb()
            }
            else -> {
                Log.e(TAG, t.message.toString())
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