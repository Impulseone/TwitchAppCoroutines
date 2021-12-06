package com.mycorp.myapplication

import androidx.paging.PagingSource
import com.mycorp.database.entities.FavoriteGameDataEntity
import com.mycorp.model.FavoriteGameData
import com.mycorp.model.GameData
import com.mycorp.model.ListItemData
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteGamesRepository {
    fun getFavoriteGames(limit: Int, offset: Int): Single<List<ListItemData<FavoriteGameData>>>
    fun getFavoriteGamesList(): PagingSource<Int, FavoriteGameDataEntity>
    fun checkIsFavorite(gameId: String): Single<Int>
    fun insertFavoriteGame(gameData: GameData): Completable
    fun deleteByGameId(gameId: String): Completable
}