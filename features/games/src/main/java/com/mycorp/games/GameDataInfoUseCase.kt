package com.mycorp.games

import com.mycorp.model.GameData
import com.mycorp.model.GameDataInfo

interface GameDataInfoUseCase {
    suspend fun fetchGameDataInfo(gameId: String): GameDataInfo
    suspend fun getGameDataInfo(gameId: String): GameDataInfo
    suspend fun insertFavorite(gameData: GameData)
    suspend fun deleteFavoriteById(gameId: String)
}