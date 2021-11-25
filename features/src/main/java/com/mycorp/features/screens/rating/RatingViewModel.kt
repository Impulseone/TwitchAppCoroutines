package com.mycorp.features.screens.rating

import com.mycorp.common.viewModel.BaseViewModel

class RatingViewModel : BaseViewModel() {

    fun updateRating(message: String) {
        showToast(message)
    }
}