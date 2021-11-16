package com.mycorp.twitchapprxjava.repository

import com.mycorp.twitchapprxjava.models.FavoriteGameData
import com.mycorp.twitchapprxjava.models.GameData
import com.mycorp.twitchapprxjava.models.ListItemData
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteGamesRepository {
    fun getFavoriteGames(limit: Int, offset: Int): Single<List<ListItemData<FavoriteGameData>>>
    fun checkIsFavorite(gameId: String): Single<Int>
    fun insertFavoriteGame(gameData: GameData): Completable
    fun deleteByGameId(gameId: String): Completable
}