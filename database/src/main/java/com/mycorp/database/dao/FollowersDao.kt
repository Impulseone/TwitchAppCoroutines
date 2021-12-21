package com.mycorp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mycorp.database.entities.FollowerInfoEntity

@Dao
interface FollowersDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(objects: List<FollowerInfoEntity>)

    @Query("select * from FollowerInfoEntity where id in (:ids)")
    suspend fun getByIds(ids: List<String>): List<FollowerInfoEntity>
}