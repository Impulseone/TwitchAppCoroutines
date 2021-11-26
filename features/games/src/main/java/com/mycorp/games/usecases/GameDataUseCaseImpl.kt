package com.mycorp.games.usecases

import com.mycorp.myapplication.FavoriteGamesRepository
import com.mycorp.myapplication.FollowersRepository
import com.mycorp.myapplication.GamesRepository
import io.reactivex.Single

class GameDataUseCaseImpl(
    private val followersRepository: FollowersRepository,
    private val gamesRepository: GamesRepository,
    private val favoriteGamesRepository: FavoriteGamesRepository
) : GameDataUseCase {
    override fun fetchGameData(gameId: String) = Single.just(gameId)
        .flatMap { id ->
            gamesRepository.getGameDataById(id)
                .flatMap { data ->
                    favoriteGamesRepository.checkIsFavorite(id).map {
                        it to data
                    }
                }
                .flatMap { (isFavorite, gameData) ->
                    followersRepository.fetchFollowers(id).map { list ->
                        Triple(isFavorite, gameData, list)
                    }
                }
        }

    override fun getGameData(gameId: String) = Single.just(gameId)
        .flatMap { id ->
            gamesRepository.getGameDataById(id)
                .flatMap { data ->
                    favoriteGamesRepository.checkIsFavorite(id).map {
                        it to data
                    }
                }
                .flatMap { (isFavorite, gameData) ->
                    followersRepository.getFollowersByGameId(id).map { list ->
                        Triple(isFavorite, gameData, list)
                    }
                }
        }
}