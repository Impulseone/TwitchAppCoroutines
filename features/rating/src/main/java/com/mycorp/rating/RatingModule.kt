package com.mycorp.rating

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ratingModule = module {
    viewModel<RatingViewModel>()
}

