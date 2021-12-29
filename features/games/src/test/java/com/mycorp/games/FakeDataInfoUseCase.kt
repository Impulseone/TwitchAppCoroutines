package com.mycorp.games

import com.mycorp.model.GameData
import com.mycorp.model.GameDataInfo

class FakeDataInfoUseCase : GameDataInfoUseCase {

    private val games = listOf(
        GameDataInfo(true, GameData("1", "sdf", "dfg", 3, 4), mutableListOf())
    )

    override suspend fun fetchGameDataInfo(gameId: String): GameDataInfo {
        return games[0]
    }

    override suspend fun getGameDataInfo(gameId: String): GameDataInfo {
        return games[0]
    }

    override suspend fun insertFavorite(gameData: GameData) {

    }

    override suspend fun deleteFavoriteById(gameId: String) {

    }
}