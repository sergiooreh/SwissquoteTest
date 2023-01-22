package com.example.swissquotetest.data.repository

import android.content.Context
import com.example.swissquotetest.data.mapper.CardDtoToCardDomainMapper
import com.example.swissquotetest.data.mapper.TransactionDtoToTransactionDomainMapper
import com.example.swissquotetest.data.remote.BankApi
import com.example.swissquotetest.domain.BankRepository
import com.example.swissquotetest.domain.models.Card
import com.example.swissquotetest.domain.models.enums.Status
import com.example.swissquotetest.domain.models.Transaction
import com.example.swissquotetest.presentation.util.Resource
import com.example.swissquotetest.presentation.util.safeCall

class BankRepositoryImpl(
    private val bankApi: BankApi,
    private val cardDomainMapper: CardDtoToCardDomainMapper,
    private val transactionDomainMapper: TransactionDtoToTransactionDomainMapper,
    private val context: Context
): BankRepository {

    override suspend fun getCards(): Resource<List<Card>> = safeCall(context) {
        val response = bankApi.getCards()
        val cardDtoList = response.body()
        if (response.isSuccessful && cardDtoList != null) {
            val cardList = cardDtoList.map {
                cardDomainMapper.toDomainModel(it)
            }
            Resource.Success(cardList)
        } else Resource.Error(response.message())
    }

    override suspend fun getExecutedTransactions(): Resource<List<Transaction>> = safeCall(context) {
        val response = bankApi.getExecutedTransactions()
        val transactionDtoList = response.body()
        if (response.isSuccessful && transactionDtoList != null) {
            val transactionList = transactionDtoList.map {
                transactionDomainMapper.toDomainModel(it, Status.EXECUTED)
            }
            Resource.Success(transactionList)
        } else Resource.Error(response.message())
    }

    override suspend fun getPendingTransactions(): Resource<List<Transaction>> = safeCall(context) {
        val response = bankApi.getPendingTransactions()
        val transactionDtoList = response.body()
        if (response.isSuccessful && transactionDtoList != null) {
            val transactionList = transactionDtoList.map {
                transactionDomainMapper.toDomainModel(it, Status.PENDING)
            }
            Resource.Success(transactionList)
        } else Resource.Error(response.message())
    }
}