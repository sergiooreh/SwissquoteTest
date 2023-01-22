package com.example.swissquotetest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TransactionDto(
    @SerializedName("id") val id: Int,
    @SerializedName("amount") val amount: String,
    @SerializedName("category") val category: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("merchand") val merchand: String,
    @SerializedName("timestamp") val timestamp: Long
)