package com.example.swissquotetest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swissquotetest.domain.BankRepository
import com.example.swissquotetest.presentation.state.MainScreenUiState
import com.example.swissquotetest.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankViewModel @Inject constructor(
    private val bankRepository: BankRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(
        MainScreenUiState(
            cards = listOf(),
            pendingTransactions = listOf(),
            executedTransactions = listOf(),
            selectedCard = 0,
            currentChunkedListIndex = 1,
            showAllTransactions = false,
            isLoading = true,
            errorMessage = null
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        getCards()
        getPendingTransactions()
        getExecutedTransactions()
    }

    private fun getCards() {
        viewModelScope.launch {
            val result = bankRepository.getCards()
            if (result is Resource.Success) {
                _uiState.update {
                    it.copy(
                        cards = result.data ?: listOf()
                    )
                }
            } else {
                _uiState.update {
                    it.copy(errorMessage = result.message)
                }
            }
        }
    }

    private fun getPendingTransactions() {
        viewModelScope.launch {
            val result = bankRepository.getPendingTransactions()
            if (result is Resource.Success) {
                _uiState.update {
                    it.copy(
                        pendingTransactions = result.data ?: listOf()
                    )
                }
            } else {
                _uiState.update {
                    it.copy(errorMessage = result.message)
                }
            }
        }
    }

    private fun getExecutedTransactions() {
        viewModelScope.launch {
            val result = bankRepository.getExecutedTransactions()
            if (result is Resource.Success) {
                _uiState.update {
                    it.copy(
                        executedTransactions = result.data ?: listOf()
                    )
                }
            } else {
                _uiState.update {
                    it.copy(errorMessage = result.message)
                }
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun changeSelectedCard(cardIndex: Int) {
        _uiState.update {
            it.copy(
                selectedCard = cardIndex
            )
        }
    }

    fun changeShowAllTransactions(show: Boolean) {
        _uiState.update {
            it.copy(
                showAllTransactions = show
            )
        }
    }

    fun changeChunkedListIndex(index: Int) {
        if (uiState.value.currentChunkedListIndex < index) {
            _uiState.update {
                it.copy(
                    currentChunkedListIndex = uiState.value.currentChunkedListIndex + 1
                )
            }
        }
    }
}