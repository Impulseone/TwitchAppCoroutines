package com.mycorp.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mycorp.database.AppDatabase
import com.mycorp.database.entities.FavoriteGameDataEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FavoriteGameDataDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: FavoriteGameDataDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.favoriteGameDataDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertFavoriteGameTest() = runBlockingTest {
        val game = FavoriteGameDataEntity(
            id = "1",
            name = "asd",
            logoUrl = "dfg",
            channelsCount = 3,
            watchersCount = 9
        )
        dao.insert(game)

        val isExist = dao.checkExist(game.id)

        assertThat(isExist).isEqualTo(1)
    }

    @Test
    fun deleteFavoriteGameTest() = runBlockingTest {
        val game = FavoriteGameDataEntity(
            id = "1",
            name = "asd",
            logoUrl = "dfg",
            channelsCount = 3,
            watchersCount = 9
        )
        dao.insert(game)
        dao.deleteByGameId(game.id)
        val isExist = dao.checkExist(game.id)

        assertThat(isExist).isEqualTo(0)
    }
}