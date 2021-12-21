package com.mycorp.common.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.mycorp.api.dto.ConvertDtoException
import com.mycorp.common.helpers.TAG
import com.mycorp.navigation.BaseNavigationFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    val showToastEvent = MutableSharedFlow<Pair<String, Int>>()
    val openScreenEvent = MutableSharedFlow<Pair<BaseNavigationFlow, NavDirections?>>()
    val connectionExceptionEvent = MutableSharedFlow<Boolean>()

    fun showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
        viewModelScope.launch {
            showToastEvent.emit(text to length)
        }
    }

    fun navigateTo(flow: BaseNavigationFlow, directions: NavDirections? = null) {
        viewModelScope.launch {
            openScreenEvent.emit(flow to directions)
        }
    }

    fun handleException(t: Throwable) {
        when (t) {
            is ConvertDtoException -> run {
                Log.e(TAG, t.message.toString())
            }
            is UnknownHostException -> run {
                Log.e(TAG, t.message.toString())
                viewModelScope.launch {
                    connectionExceptionEvent.emit(true)
                }
                getDataFromDb()
            }
            else -> {
                Log.e(TAG, t.message.toString())
            }
        }
    }

    open fun getDataFromDb() {}
}