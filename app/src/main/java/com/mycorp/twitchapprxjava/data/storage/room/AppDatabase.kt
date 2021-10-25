package com.mycorp.twitchapprxjava.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mycorp.twitchapprxjava.data.storage.room.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.data.storage.room.entities.GameDataEntity

@Database(
    entities = [GameDataEntity::class, FollowerInfoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val gameDataDao: GameDataDao

    abstract val followersDao: FollowersDao
}