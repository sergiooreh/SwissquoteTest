package com.example.swissquotetest.data.repository

import com.example.swissquotetest.domain.BankRepository
import com.example.swissquotetest.domain.models.Card
import com.example.swissquotetest.domain.models.enums.Category
import com.example.swissquotetest.domain.models.enums.Status
import com.example.swissquotetest.domain.models.Transaction
import com.example.swissquotetest.presentation.util.Resource

class FakeBankRepositoryImplTest: BankRepository {
    private val _cards = listOf(
        Card(
            id = 1,
            amount = "100",
            currency = "CHF",
            numbers = "1234 1234 1234 1234",
            type = "Debit"
        )
    )

    private val _transactions = listOf(
        Transaction(
            id = 1,
            amount = "100",
            category = Category.SERVICE,
            currency = "CHF",
            merchand = "sncf",
            date = "22.01.2023 18:03",
            status = Status.EXECUTED
        )
    )

    override suspend fun getCards(): Resource<List<Card>> {
        return Resource.Success(_cards)
    }

    override suspend fun getExecutedTransactions(): Resource<List<Transaction>> {
        return Resource.Success(_transactions)
    }

    override suspend fun getPendingTransactions(): Resource<List<Transaction>> {
        return Resource.Success(_transactions)
    }

}