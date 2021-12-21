package com.mycorp.myapplication

import androidx.paging.PagingSource
import com.mycorp.database.entities.FavoriteGameDataEntity
import com.mycorp.model.GameData

interface FavoriteGamesRepository {
    fun getFavoriteGames(): PagingSource<Int, FavoriteGameDataEntity>
    suspend fun checkIsFavorite(gameId: String): Boolean
    suspend fun insertFavoriteGame(gameData: GameData)
    suspend fun deleteByGameId(gameId: String)
}