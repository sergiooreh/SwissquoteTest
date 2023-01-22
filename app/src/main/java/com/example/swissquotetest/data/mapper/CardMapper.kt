package com.example.swissquotetest.data.mapper

import com.example.swissquotetest.data.remote.dto.CardDto
import com.example.swissquotetest.domain.models.Card
import javax.inject.Inject

interface CardDtoToCardDomainMapper {
    fun toDomainModel(cardDto: CardDto): Card
}

class CardDtoToCardDomainMapperImpl @Inject constructor(

): CardDtoToCardDomainMapper {

    override fun toDomainModel(cardDto: CardDto): Card {
        return Card(
            id = cardDto.id,
            amount = cardDto.amount,
            currency = cardDto.currency,
            numbers = cardDto.numbers,
            type = cardDto.type
        )
    }
}