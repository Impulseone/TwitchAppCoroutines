package com.mycorp.twitchapprxjava.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycorp.twitchapprxjava.data.network.NetworkControllerImpl
import com.mycorp.twitchapprxjava.data.repository.RepositoryImplementation
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromNetworkUseCase

class MainViewModelFactory (context: Context):ViewModelProvider.Factory {

    private val getFromNetworkUseCase by lazy {
        GetFromNetworkUseCase(
            RepositoryImplementation(
                NetworkControllerImpl()
            )
        )
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(getFromNetworkUseCase) as T
    }
}