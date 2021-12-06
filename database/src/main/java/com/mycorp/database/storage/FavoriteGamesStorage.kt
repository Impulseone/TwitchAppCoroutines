package com.mycorp.database.storage

import androidx.paging.PagingSource
import com.mycorp.database.entities.FavoriteGameDataEntity
import com.mycorp.model.GameData
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteGamesStorage {
    fun getFavoriteGamesList(): PagingSource<Int, FavoriteGameDataEntity>
    fun checkIsFavorite(gameId: String): Single<Int>
    fun insertFavoriteGame(gameData: GameData): Completable
    fun deleteFavoriteByGameId(gameId: String): Completable
}