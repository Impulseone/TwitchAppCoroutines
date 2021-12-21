package com.mycorp.model

class GameDataInfo(
    val isFavorite: Boolean,
    val gameData: GameData,
    val followers: List<FollowerInfo>
)