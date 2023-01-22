package com.example.swissquotetest.domain.models

data class Card(
    val id: Int,
    val amount: String,
    val currency: String,
    val numbers: String,
    val type: String
)