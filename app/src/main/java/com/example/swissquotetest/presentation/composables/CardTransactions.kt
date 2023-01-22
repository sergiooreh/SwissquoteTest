package com.example.swissquotetest.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swissquotetest.domain.models.Transaction
import com.example.swissquotetest.presentation.ui.theme.Orange

@Composable
fun CardTransactions(
    transactions: List<Transaction>,
    modifier: Modifier,
    onViewAllClick: () -> Unit,
    onTransactionClick: (Transaction) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(
                        id = com.example.swissquotetest.R.string.card_transactions
                    ),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(
                        id = com.example.swissquotetest.R.string.view_all
                    ),
                    color = Orange,
                    modifier = Modifier
                        .clickable {
                            onViewAllClick()
                        }
                )
            }
            LazyColumn {
                itemsIndexed(transactions) { index, transaction ->
                    TransactionItem(
                        transaction = transaction
                    ) {
                        onTransactionClick(transaction)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun CardTransactionsPrev() {
    CardTransactions(
        transactions = listOf(),
        modifier = Modifier,
        onViewAllClick = {},
        onTransactionClick = {}
    )
}