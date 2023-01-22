package com.example.swissquotetest.presentation.screens

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.swissquotetest.R
import com.example.swissquotetest.domain.models.enums.Category
import com.example.swissquotetest.domain.models.enums.Status
import com.example.swissquotetest.domain.models.Transaction
import com.example.swissquotetest.presentation.state.MainScreenUiState
import com.example.swissquotetest.presentation.ui.theme.SwissquoteTestTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun availableAmountIsVisibleOnStart() = runBlocking<Unit> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val fakeUiState = MainScreenUiState(
            cards = listOf(),
            pendingTransactions = listOf(),
            executedTransactions = listOf(),
            selectedCard = 0,
            showAllTransactions = false,
            currentChunkedListIndex = 1,
            isLoading = false,
            errorMessage = null
        )

        composeRule.setContent {
            SwissquoteTestTheme {
                MainScreen(
                    uiState = fakeUiState,
                    onSelectedCardChanged = {},
                    onTransactionClick = {},
                    onShowAllTransactionsClick = {},
                    onSelectChunkedListIndex = {}
                )
            }
        }

        composeRule
            .onNodeWithText(context.getString(R.string.available_amount))
            .assertIsDisplayed()
        composeRule
            .onNodeWithTag("sum")
            .assertTextEquals("0.00 CHF")
    }

    @Test
    fun transactionAmountIsWithMinus() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val fakeUiState = MainScreenUiState(
            cards = listOf(),
            pendingTransactions = listOf(
                Transaction(
                    id = 0,
                    amount = "56",
                    category = Category.ENERGY,
                    currency = "CHF",
                    merchand = "Samsung",
                    date = "22.01.23 18:35",
                    status = Status.PENDING
                )
            ),
            executedTransactions = listOf(),
            selectedCard = 0,
            showAllTransactions = false,
            currentChunkedListIndex = 1,
            isLoading = false,
            errorMessage = null
        )

        composeRule.setContent {
            SwissquoteTestTheme {
                MainScreen(
                    uiState = fakeUiState,
                    onSelectedCardChanged = {},
                    onTransactionClick = {},
                    onShowAllTransactionsClick = {},
                    onSelectChunkedListIndex = {}
                )
            }
        }

        composeRule
            .onNodeWithText("-${fakeUiState.pendingTransactions.first().amount}")
            .assertIsDisplayed()
    }
}