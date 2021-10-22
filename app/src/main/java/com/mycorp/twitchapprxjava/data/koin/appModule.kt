package com.mycorp.twitchapprxjava.data.koin

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import com.mycorp.twitchapprxjava.BuildConfig
import com.mycorp.twitchapprxjava.data.network.NetworkController
import com.mycorp.twitchapprxjava.data.network.NetworkControllerImpl
import com.mycorp.twitchapprxjava.data.network.retrofit.ApiService
import com.mycorp.twitchapprxjava.data.repository.RepositoryImplementation
import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.room.AppDatabase
import com.mycorp.twitchapprxjava.data.storage.room.RoomStorage
import com.mycorp.twitchapprxjava.domain.repository.Repository
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapprxjava.domain.use_cases.GetFromServerUseCase
import com.mycorp.twitchapprxjava.presentation.viewModel.GamesListViewModel
import com.mycorp.twitchapprxjava.presentation.viewModel.RatingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<NetworkController> { NetworkControllerImpl(get()) }

    single<Storage> { RoomStorage(get()) }

    single<Repository> { RepositoryImplementation(get(), get()) }

    single { GetFromServerUseCase(get()) }

    single { GetFromDbUseCase(get()) }

    single { provideRetrofit() }

    single { provideRetrofitService(get()) }

    single { provideRoomDb(androidContext()) }

    single { get<AppDatabase>().gameDataDao}

    viewModel<GamesListViewModel>()

    viewModel<RatingViewModel>()
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

private fun provideRoomDb(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "games_database"
    )
        .build()
}

private fun provideRetrofitService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}