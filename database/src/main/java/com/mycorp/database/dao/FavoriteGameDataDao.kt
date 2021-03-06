package com.mycorp.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mycorp.database.entities.FavoriteGameDataEntity

@Dao
interface FavoriteGameDataDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(favoriteGameDataEntity: FavoriteGameDataEntity)

    @Query("SELECT COUNT() FROM FavoriteGameDataEntity WHERE id = :id")
    suspend fun checkExist(id: String): Int

    @Query("SELECT * FROM FavoriteGameDataEntity")
    fun getAll(): PagingSource<Int, FavoriteGameDataEntity>

    @Query("DELETE FROM FavoriteGameDataEntity WHERE id = :gameId")
    suspend fun deleteByGameId(gameId: String)
}