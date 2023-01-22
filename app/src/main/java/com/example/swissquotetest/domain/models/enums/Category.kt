package com.example.swissquotetest.domain.models.enums

import com.example.swissquotetest.R

enum class Category(val image: Int) {
    TRANSPORT(R.drawable.bus),
    SERVICE(R.drawable.services),
    SHOPPING(R.drawable.shopping),
    ENERGY(R.drawable.energy)
}