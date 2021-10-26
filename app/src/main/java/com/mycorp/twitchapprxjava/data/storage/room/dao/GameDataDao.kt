package com.mycorp.twitchapprxjava.data.storage.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.mycorp.twitchapprxjava.data.storage.room.entities.GameDataEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GameDataDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(objects: List<GameDataEntity>): Completable

    @Query("SELECT * FROM GameDataEntity")
    fun getAllGames(): Single<List<GameDataEntity>>
}