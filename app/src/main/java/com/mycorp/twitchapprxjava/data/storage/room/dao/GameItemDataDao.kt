package com.mycorp.twitchapprxjava.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mycorp.twitchapprxjava.data.storage.room.entities.GameItemDataEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GameItemDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gameItemDataEntity: GameItemDataEntity): Completable

    @Query("SELECT * FROM GameItemDataEntity where id=:id")
    fun getById(id: String): Single<GameItemDataEntity>
}