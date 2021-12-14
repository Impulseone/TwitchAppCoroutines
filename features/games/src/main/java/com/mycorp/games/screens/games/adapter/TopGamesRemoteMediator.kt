package com.mycorp.games.screens.games.adapter

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bumptech.glide.load.HttpException
import com.mycorp.database.entities.GameDataEntity
import com.mycorp.myapplication.GamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class TopGamesRemoteMediator(
    private val gamesRepository: GamesRepository
) : RemoteMediator<Int, GameDataEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameDataEntity>
    ): MediatorResult {
        return try {
            val gamesList = gamesRepository.fetchGamesDataList(state.config.pageSize, 0)
            gamesRepository.insertGamesDataSuspend(gamesList)
            MediatorResult.Success(true)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }
}