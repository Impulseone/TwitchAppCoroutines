package com.mycorp.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mycorp.database.entities.GameDataEntity

@Dao
interface GameDataDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAllSuspend(objects: List<GameDataEntity>)

    @Query("SELECT * FROM GameDataEntity")
    fun getAllGamesPaging(): PagingSource<Int, GameDataEntity>

    @Query("SELECT * FROM GameDataEntity where id=:id")
    suspend fun getGameByIdSuspend(id: String): GameDataEntity
}