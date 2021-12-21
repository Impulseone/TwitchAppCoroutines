package com.mycorp.myapplication

import androidx.paging.PagingSource
import com.mycorp.database.entities.GameDataEntity
import com.mycorp.model.GameData

interface GamesRepository {

    suspend fun fetchGamesDataList(limit: Int, offset: Int): List<GameData>

    suspend fun deleteAllGames()

    fun getGamesPaging(): PagingSource<Int, GameDataEntity>

    suspend fun getGameDataById(id: String): GameData

    suspend fun insertGamesData(gameDataList: List<GameData>)
}