package com.mycorp.twitchapprxjava.database.storage

import com.mycorp.twitchapprxjava.database.entities.FavoriteGameDataEntity
import com.mycorp.twitchapprxjava.models.GameData
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteGamesStorage {
    fun getFavoriteGames(limit: Int, offset: Int): Single<List<FavoriteGameDataEntity>>
    fun checkIsFavorite(gameId: String): Single<Int>
    fun insertFavoriteGame(gameData: GameData): Completable
    fun deleteFavoriteByGameId(gameId: String): Completable
}