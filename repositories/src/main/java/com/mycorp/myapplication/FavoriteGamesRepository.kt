package com.mycorp.myapplication

import androidx.paging.PagingSource
import com.mycorp.database.entities.FavoriteGameDataEntity
import com.mycorp.model.FavoriteGameData
import com.mycorp.model.GameData
import com.mycorp.model.ListItemData
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteGamesRepository {
    fun getFavoriteGames(): PagingSource<Int, FavoriteGameDataEntity>
    suspend fun checkIsFavorite(gameId: String): Boolean
    suspend fun insertFavoriteGame(gameData: GameData)
    suspend fun deleteByGameId(gameId: String)
}