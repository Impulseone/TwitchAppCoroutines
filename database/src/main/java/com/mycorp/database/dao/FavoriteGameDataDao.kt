package com.mycorp.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mycorp.database.entities.FavoriteGameDataEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteGameDataDao {
    @Insert(onConflict = REPLACE)
    fun insert(favoriteGameDataEntity: FavoriteGameDataEntity): Completable

    @Query("SELECT COUNT() FROM FavoriteGameDataEntity WHERE id = :id")
    fun checkExist(id: String): Single<Int>

    @Query("select * from FavoriteGameDataEntity")
    fun getAllList(): PagingSource<Int, FavoriteGameDataEntity>

    @Query("select * from FavoriteGameDataEntity limit :limit offset :offset")
    fun getAll(limit: Int, offset: Int): Single<List<FavoriteGameDataEntity>>

    @Query("DELETE FROM FavoriteGameDataEntity WHERE id = :gameId")
    fun deleteByGameId(gameId: String): Completable
}