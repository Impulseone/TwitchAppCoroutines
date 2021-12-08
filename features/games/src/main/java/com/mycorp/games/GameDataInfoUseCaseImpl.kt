package com.mycorp.games

import com.mycorp.model.GameData
import com.mycorp.model.GameDataInfo
import com.mycorp.myapplication.FavoriteGamesRepository
import com.mycorp.myapplication.FollowersRepository
import com.mycorp.myapplication.GamesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GameDataInfoUseCaseImpl(
    private val followersRepository: FollowersRepository,
    private val gamesRepository: GamesRepository,
    private val favoriteGamesRepository: FavoriteGamesRepository,
    private val dispatcher: CoroutineDispatcher
) : GameDataInfoUseCase {
    override suspend fun fetchGameDataInfo(gameId: String): GameDataInfo {
        val gameDataInfo: GameDataInfo
        withContext(dispatcher) {
            val gameData = gamesRepository.getGameDataByIdSuspend(gameId)
            val isFavorite = favoriteGamesRepository.checkIsFavorite(gameId)
            val followers = followersRepository.fetchFollowers(gameId)
            gameDataInfo = GameDataInfo(isFavorite, gameData, followers)
        }
        return gameDataInfo
    }

    override suspend fun getGameDataInfo(gameId: String): GameDataInfo {
        val gameDataInfo: GameDataInfo
        withContext(dispatcher) {
            val gameData = gamesRepository.getGameDataByIdSuspend(gameId)
            val isFavorite = favoriteGamesRepository.checkIsFavorite(gameId)
            val followers = followersRepository.getFollowersByGameId(gameId)
            gameDataInfo = GameDataInfo(isFavorite, gameData, followers)
        }
        return gameDataInfo
    }

    override suspend fun insertFavorite(gameData: GameData) {
        withContext(dispatcher) {
            favoriteGamesRepository.insertFavoriteGame(gameData)
        }
    }

    override suspend fun deleteFavoriteById(gameId: String) {
        withContext(dispatcher) {
            favoriteGamesRepository.deleteByGameId(gameId)
        }
    }

}