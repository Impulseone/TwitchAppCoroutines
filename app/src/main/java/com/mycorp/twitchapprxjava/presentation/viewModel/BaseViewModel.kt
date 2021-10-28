package com.mycorp.twitchapprxjava.presentation.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.mycorp.twitchapprxjava.data.storage.model.topGamesResponse.ConvertDtoException
import com.mycorp.twitchapprxjava.presentation.viewModel.helpers.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    val showToast = SingleLiveEvent<Pair<String, Int>>()

    fun showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
        showToast.value = text to length
    }

    fun handleException(e: Exception) {
        when (e) {
            is ConvertDtoException -> run {
                Log.e("Dto", e.message.toString())
            }
            else -> {
                Log.e("Error", e.message.toString())
            }
        }
    }
}