package com.mycorp.twitchapprxjava.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mycorp.twitchapprxjava.database.room.dao.FavoriteGameDataDao
import com.mycorp.twitchapprxjava.database.room.dao.FollowersDao
import com.mycorp.twitchapprxjava.database.room.dao.GameDataDao
import com.mycorp.twitchapprxjava.database.room.dao.GameFollowersDao
import com.mycorp.twitchapprxjava.database.room.entities.FavoriteGameDataEntity
import com.mycorp.twitchapprxjava.database.room.entities.FollowerInfoEntity
import com.mycorp.twitchapprxjava.database.room.entities.GameDataEntity
import com.mycorp.twitchapprxjava.database.room.entities.GameFollowersEntity

@Database(
    entities = [GameDataEntity::class, FollowerInfoEntity::class, FavoriteGameDataEntity::class, GameFollowersEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val gameDataDao: GameDataDao

    abstract val followersDao: FollowersDao

    abstract val favoriteGameDataDao: FavoriteGameDataDao

    abstract val gameFollowersDao: GameFollowersDao
}