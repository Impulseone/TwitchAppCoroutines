package com.mycorp.twitchapprxjava.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mycorp.twitchapprxjava.data.storage.room.dao.FollowersDao
import com.mycorp.twitchapprxjava.data.storage.room.dao.GameDataDao
import com.mycorp.twitchapprxjava.data.storage.room.dao.GameItemDataDao
import com.mycorp.twitchapprxjava.data.storage.room.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.data.storage.room.entities.GameDataEntity
import com.mycorp.twitchapprxjava.data.storage.room.entities.GameItemDataEntity

@Database(
    entities = [GameDataEntity::class, FollowerInfoEntity::class, GameItemDataEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val gameDataDao: GameDataDao

    abstract val followersDao: FollowersDao

    abstract val gameItemDataDao:GameItemDataDao
}