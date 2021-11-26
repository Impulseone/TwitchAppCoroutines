package com.mycorp.games

import com.mycorp.model.FollowerInfo
import com.mycorp.model.GameData
import io.reactivex.Completable
import io.reactivex.Single

interface GameDataUseCase {
    fun fetchGameData(gameId: String): Single<Triple<Int, GameData, List<FollowerInfo>>>
    fun getGameData(gameId: String): Single<Triple<Int, GameData, List<FollowerInfo>>>
    fun insertFavorite(gameData:GameData): Completable
    fun deleteFavoriteById(gameId:String): Completable
}