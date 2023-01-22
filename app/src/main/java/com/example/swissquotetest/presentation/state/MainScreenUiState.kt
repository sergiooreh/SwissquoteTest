package com.example.swissquotetest.presentation.state

import com.example.swissquotetest.domain.models.Card
import com.example.swissquotetest.domain.models.Transaction

data class MainScreenUiState(
    val cards: List<Card>,
    val pendingTransactions: List<Transaction>,
    val executedTransactions: List<Transaction>,
    val selectedCard: Int,
    val showAllTransactions: Boolean,
    val currentChunkedListIndex: Int,
    val isLoading: Boolean,
    val errorMessage: String?
)