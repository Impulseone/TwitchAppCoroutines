package com.mycorp.twitchapprxjava.database.model

import android.os.Parcelable
import com.mycorp.twitchapprxjava.database.room.entities.FavoriteGameDataEntity
import com.mycorp.twitchapprxjava.database.room.entities.GameDataEntity
import com.mycorp.twitchapprxjava.screens.games.adapter.GameListItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameData(
    val id: String,
    val name: String,
    val logoUrl: String,
    val channelsCount: Int,
    val watchersCount: Int,
) : Parcelable {
    companion object {
        fun fromEntity(gameDataEntity: GameDataEntity): GameData {
            return GameData(
                gameDataEntity.id,
                gameDataEntity.name,
                gameDataEntity.logoUrl,
                gameDataEntity.channelsCount,
                gameDataEntity.watchersCount
            )
        }
    }
}
