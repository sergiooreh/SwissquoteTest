package com.example.swissquotetest.data.mapper

import android.icu.text.SimpleDateFormat
import com.example.swissquotetest.data.remote.dto.TransactionDto
import com.example.swissquotetest.domain.models.enums.Category
import com.example.swissquotetest.domain.models.enums.Status
import com.example.swissquotetest.domain.models.Transaction
import java.util.*
import javax.inject.Inject

interface TransactionDtoToTransactionDomainMapper {
    fun toDomainModel(transactionDto: TransactionDto, status: Status): Transaction
}

class TransactionDtoToTransactionDomainMapperImpl @Inject constructor(
    private val calendar: Calendar
): TransactionDtoToTransactionDomainMapper {

    override fun toDomainModel(transactionDto: TransactionDto, status: Status): Transaction {
        calendar.timeInMillis = transactionDto.timestamp * 1000
        val date = SimpleDateFormat(datePattern, Locale.ROOT).format(calendar.time)
        return Transaction(
            id = transactionDto.id,
            amount = transactionDto.amount,
            category = Category.valueOf(transactionDto.category.uppercase(Locale.ROOT)),
            currency = transactionDto.currency,
            date = date,
            merchand = transactionDto.merchand,
            status = status
        )
    }

    companion object {
        const val datePattern = "dd.MM.yyyy HH:mm"
    }
}