package com.mycorp.twitchapprxjava.data.storage.model

class GameItemData(
    val id: String,
    val name: String,
    val photoUrl: String,
    var followersIds: List<String> = mutableListOf(),
    var isLiked: Boolean = false
) {
    companion object {
        fun fromGameData(gameData: GameData, followers: List<FollowerInfo>): GameItemData {
            return GameItemData(
                gameData.id.toString(),
                gameData.name,
                gameData.logoUrl,
                followers.map { it.followerId }
            )
        }
    }
}