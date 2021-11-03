package com.mycorp.twitchapprxjava.di

import com.mycorp.twitchapprxjava.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.use_cases.GetFromServerUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetFromServerUseCase(get()) }
    single { GetFromDbUseCase(get()) }
}