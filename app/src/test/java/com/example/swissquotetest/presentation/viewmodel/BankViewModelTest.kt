package com.example.swissquotetest.presentation.viewmodel

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.swissquotetest.data.repository.FakeBankRepositoryImplTest
import com.example.swissquotetest.domain.BankRepository
import com.example.swissquotetest.presentation.state.MainScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BankViewModelTest {
    private lateinit var viewModel: BankViewModel
    private lateinit var repository: BankRepository

    @Before
    fun setUp() {
        repository = FakeBankRepositoryImplTest()
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = BankViewModel(
            bankRepository = repository
        )
    }

    @Test
    fun `Initial state is correct`() = runTest {
        viewModel.uiState.test {
            val initialState = awaitItem()
            val initState = MainScreenUiState(
                cards = listOf(),
                pendingTransactions = listOf(),
                executedTransactions = listOf(),
                selectedCard = 0,
                currentChunkedListIndex = 1,
                showAllTransactions = false,
                isLoading = true,
                errorMessage = null
            )
            assertThat(initialState).isEqualTo(initState)
        }
    }
}