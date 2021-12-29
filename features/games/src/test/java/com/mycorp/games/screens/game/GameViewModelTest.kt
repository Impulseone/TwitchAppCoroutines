package com.mycorp.games.screens.game

import app.cash.turbine.test
import com.mycorp.games.FakeDataInfoUseCase
import com.mycorp.games.GameDataInfoUseCase
import com.mycorp.games.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.inject
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
class GameViewModelTest : KoinTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single<GameDataInfoUseCase> { FakeDataInfoUseCase() }
                single { GameViewModel(get()) }
            })
    }

    val viewModel: GameViewModel by inject()

    @Test
    fun firstTest() {
        assertNotNull(viewModel)
    }

    @Test
    fun secondTest(): Unit = runBlocking {
        viewModel.gameDataInfoFlow.test {
            viewModel.init("jkjk")
            assertEquals("1", awaitItem().gameData.id)
            cancelAndConsumeRemainingEvents()
        }
    }
}