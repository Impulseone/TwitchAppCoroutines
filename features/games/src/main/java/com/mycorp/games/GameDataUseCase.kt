package com.mycorp.games

import com.mycorp.model.FollowerInfo
import com.mycorp.model.GameData
import io.reactivex.Completable
import io.reactivex.Single

interface GameDataUseCase {
    suspend fun fetchGameDataSuspend(gameId: String): Triple<Int, GameData, List<FollowerInfo>>
    suspend fun getGameDataSuspend(gameId: String): Triple<Int, GameData, List<FollowerInfo>>
    fun insertFavorite(gameData: GameData): Completable
    fun deleteFavoriteById(gameId: String): Completable
}