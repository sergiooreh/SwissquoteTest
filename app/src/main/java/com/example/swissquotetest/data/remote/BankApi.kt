package com.example.swissquotetest.data.remote

import com.example.swissquotetest.data.remote.dto.CardDto
import com.example.swissquotetest.data.remote.dto.TransactionDto
import retrofit2.Response
import retrofit2.http.GET

interface BankApi {

    @GET("v1/c145c7af-b1ea-42f3-ae85-f6fabe6793c1")
    suspend fun getCards(): Response<List<CardDto>>

    @GET("v1/22812b7a-d2dc-4377-bef9-843b674852c0")
    suspend fun getExecutedTransactions(): Response<List<TransactionDto>>

    @GET("v1/b4dbd7be-2ea0-4fc2-837f-be4458f4dbc5")
    suspend fun getPendingTransactions(): Response<List<TransactionDto>>
}