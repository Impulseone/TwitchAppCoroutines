package com.mycorp.games.screens.games.adapter

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bumptech.glide.load.HttpException
import com.mycorp.database.entities.GameDataEntity
import com.mycorp.database.entities.RemoteKeys
import com.mycorp.database.storage.RemoteKeysStorage
import com.mycorp.myapplication.GamesRepository
import java.io.IOException
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class TopGamesRemoteMediator(
    private val gamesRepository: GamesRepository,
    private val remoteKeysStorage: RemoteKeysStorage
) : RemoteMediator<Int, GameDataEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameDataEntity>
    ): MediatorResult {

        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> return pageKeyData
            else -> pageKeyData as Int
        }

        return try {
            val response = gamesRepository.fetchGamesDataList(state.config.pageSize, page)
            val isEndOfList = response.isEmpty()
            if (loadType == LoadType.REFRESH) {
                remoteKeysStorage.clearRemoteKeys()
                gamesRepository.deleteAllGames()
            }
            val prevKey = if (page == 0) null else page - 1
            val nextKey = if (isEndOfList) null else page + 1
            val keys = response.map {
                RemoteKeys(gameId = it.id, prevKey = prevKey, nextKey = nextKey)
            }
            remoteKeysStorage.insertAll(keys)
            gamesRepository.insertGamesDataSuspend(response)
            MediatorResult.Success(true)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    /**
     * this returns the page key or the final end of list success result
     */
    suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, GameDataEntity>): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: 0
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                    ?: throw InvalidObjectException("Invalid state, key should not be null")
                //end of list condition reached
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
        }
    }

    /**
     * get the last remote key inserted which had the data
     */
    private suspend fun getLastRemoteKey(state: PagingState<Int, GameDataEntity>): RemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { gameDataEntity -> remoteKeysStorage.remoteKeysGameId(gameDataEntity.id) }
    }

    /**
     * get the first remote key inserted which had the data
     */
    private suspend fun getFirstRemoteKey(state: PagingState<Int, GameDataEntity>): RemoteKeys? {
        return state.pages
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { gameDataEntity -> remoteKeysStorage.remoteKeysGameId(gameDataEntity.id) }
    }

    /**
     * get the closest remote key inserted which had the data
     */
    private suspend fun getClosestRemoteKey(state: PagingState<Int, GameDataEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { gameId ->
                remoteKeysStorage.remoteKeysGameId(gameId)
            }
        }
    }

}