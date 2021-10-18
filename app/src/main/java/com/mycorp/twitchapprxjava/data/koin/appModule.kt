package com.mycorp.twitchapprxjava.data.koin

import com.mycorp.twitchapprxjava.data.network.NetworkController
import com.mycorp.twitchapprxjava.data.network.NetworkControllerImpl
import com.mycorp.twitchapprxjava.data.repository.RepositoryImplementation
import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.room.RoomStorage
import com.mycorp.twitchapprxjava.domain.repository.Repository
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromServerUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single<NetworkController> { NetworkControllerImpl() }

    single<Storage> { RoomStorage(androidContext()) }

    single<Repository> { RepositoryImplementation(get(), get()) }

    single { GetFromServerUseCase(get()) }

    single { GetFromDbUseCase(get()) }
}
