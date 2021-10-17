package com.mycorp.twitchapprxjava.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromNetworkUseCase
import org.koin.java.KoinJavaComponent.get

class MainViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(
            get(clazz = GetFromNetworkUseCase::class.java),
            get(clazz = GetFromDbUseCase::class.java)
        ) as T
    }
}