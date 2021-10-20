package com.mycorp.twitchapprxjava.presentation.viewModel

import android.widget.Toast
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val showToast = SingleLiveEvent<Pair<String, Int>>()

    fun showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
        showToast.value = text to length
    }
}