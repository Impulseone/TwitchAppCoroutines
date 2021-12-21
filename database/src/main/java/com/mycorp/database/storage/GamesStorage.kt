package com.mycorp.database.storage

import androidx.paging.PagingSource
import com.mycorp.database.entities.GameDataEntity
import com.mycorp.model.GameData

interface GamesStorage {
    fun getGamesDataPaging(): PagingSource<Int, GameDataEntity>
    suspend fun deleteAllGames()
    suspend fun getGameDataEntityById(id: String): GameDataEntity
    suspend fun insertGamesData(gamesData: List<GameData>)
}