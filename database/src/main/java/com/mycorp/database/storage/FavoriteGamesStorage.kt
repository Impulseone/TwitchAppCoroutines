package com.mycorp.database.storage

import androidx.paging.PagingSource
import com.mycorp.database.entities.FavoriteGameDataEntity
import com.mycorp.model.GameData
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteGamesStorage {
    fun getFavoriteGamesList(): PagingSource<Int, FavoriteGameDataEntity>
    suspend fun checkIsFavorite(gameId: String): Boolean
    suspend fun insertFavoriteGame(gameData: GameData)
    suspend fun deleteFavoriteByGameId(gameId: String)
}