package com.mycorp.twitchapprxjava.database

import androidx.paging.DataSource
import com.mycorp.twitchapprxjava.models.FollowerInfo
import com.mycorp.twitchapprxjava.models.GameData
import com.mycorp.twitchapprxjava.database.room.entities.FavoriteGameDataEntity
import com.mycorp.twitchapprxjava.database.room.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.database.room.entities.GameDataEntity
import io.reactivex.Completable
import io.reactivex.Single

interface Storage {

    fun getGamesData(): DataSource.Factory<Int, GameDataEntity>
    fun getGameDataEntityById(id: String): Single<GameDataEntity>
    fun getFollowersByIds(followerIds: List<String>): Single<List<FollowerInfoEntity>>
    fun getFollowersIdByGameId(gameId: String): Single<List<String>>
    fun getFavoriteGames(limit: Int, offset: Int): Single<List<FavoriteGameDataEntity>>

    fun insertGamesData(gamesData: List<GameData>): Completable
    fun insertFollowersData(followersData: List<FollowerInfo>, gameId: String): Completable

    fun checkIsFavorite(gameId: String): Single<Int>
    fun insertFavoriteGame(gameData: GameData): Completable
    fun deleteFavoriteByGameId(gameId: String): Completable
}