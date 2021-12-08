package com.mycorp.games

import com.mycorp.model.FollowerInfo
import com.mycorp.model.GameData
import io.reactivex.Completable
import io.reactivex.Single

interface GameDataUseCase {
    suspend fun fetchGameData(gameId: String): Triple<Int, GameData, List<FollowerInfo>>
    suspend fun getGameData(gameId: String): Triple<Int, GameData, List<FollowerInfo>>
    suspend fun insertFavorite(gameData: GameData)
    suspend fun deleteFavoriteById(gameId: String)
}