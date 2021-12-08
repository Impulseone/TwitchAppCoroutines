package com.mycorp.games

import com.mycorp.model.FollowerInfo
import com.mycorp.model.GameData
import com.mycorp.myapplication.FavoriteGamesRepository
import com.mycorp.myapplication.FollowersRepository
import com.mycorp.myapplication.GamesRepository
import io.reactivex.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GameDataUseCaseImpl(
    private val followersRepository: FollowersRepository,
    private val gamesRepository: GamesRepository,
    private val favoriteGamesRepository: FavoriteGamesRepository,
    private val dispatcher: CoroutineDispatcher
) : GameDataUseCase {
    override suspend fun fetchGameData(gameId: String): Triple<Int, GameData, List<FollowerInfo>> {
        val triple: Triple<Int, GameData, List<FollowerInfo>>
        withContext(dispatcher) {
            val gameData = gamesRepository.getGameDataByIdSuspend(gameId)
            val isFavorite = favoriteGamesRepository.checkIsFavoriteSuspend(gameId)
            val followers = followersRepository.fetchFollowersSuspend(gameId)
            triple = Triple(isFavorite, gameData, followers)
        }
        return triple
    }

    override suspend fun getGameData(gameId: String): Triple<Int, GameData, List<FollowerInfo>> {
        val triple: Triple<Int, GameData, List<FollowerInfo>>
        withContext(dispatcher) {
            val gameData = gamesRepository.getGameDataByIdSuspend(gameId)
            val isFavorite = favoriteGamesRepository.checkIsFavoriteSuspend(gameId)
            val followers = followersRepository.getFollowersByGameIdSuspend(gameId)
            triple = Triple(isFavorite, gameData, followers)
        }
        return triple
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