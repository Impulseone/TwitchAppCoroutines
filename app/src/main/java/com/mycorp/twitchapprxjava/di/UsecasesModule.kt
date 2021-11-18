package com.mycorp.twitchapprxjava.di

import com.mycorp.twitchapprxjava.usecases.GetGameDataUseCase
import com.mycorp.twitchapprxjava.usecases.GetGameDataUseCaseImpl
import org.koin.dsl.module

val usecasesModule = module{
    single<GetGameDataUseCase> { GetGameDataUseCaseImpl(get(),get(),get()) }
}