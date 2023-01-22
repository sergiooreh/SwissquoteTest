package com.example.swissquotetest.domain

import com.example.swissquotetest.domain.models.Card
import com.example.swissquotetest.domain.models.Transaction
import com.example.swissquotetest.presentation.util.Resource

interface BankRepository {

    suspend fun getCards(): Resource<List<Card>>

    suspend fun getExecutedTransactions(): Resource<List<Transaction>>

    suspend fun getPendingTransactions(): Resource<List<Transaction>>
}