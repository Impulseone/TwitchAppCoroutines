package com.mycorp.twitchapprxjava.database.room.dao

import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.mycorp.twitchapprxjava.database.room.entities.GameDataEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GameDataDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(objects: List<GameDataEntity>): Completable

    @Query("SELECT * FROM GameDataEntity")
    fun getAllGames(): DataSource.Factory<Int, GameDataEntity>

    @Query("SELECT * FROM GameDataEntity where id=:id")
    fun getGameById(id: String): Single<GameDataEntity>
}