package com.example.swissquotetest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CardDto(
    @SerializedName("id") val id: Int,
    @SerializedName("amount") val amount: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("numbers") val numbers: String,
    @SerializedName("type") val type: String
)