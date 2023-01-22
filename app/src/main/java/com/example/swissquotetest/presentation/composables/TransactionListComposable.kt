package com.example.swissquotetest.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.swissquotetest.R
import com.example.swissquotetest.domain.models.Transaction
import com.example.swissquotetest.presentation.state.MainScreenUiState
import com.example.swissquotetest.presentation.ui.theme.Orange

@Composable
fun TransactionListComposable(
    list: List<Transaction>,
    onSelectChunkedListIndex: (Int) -> Unit,
    onShowAllTransactionsClick: (Boolean) -> Unit,
    onTransactionClick: (Transaction) -> Unit
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            IconButton(onClick = {
                onShowAllTransactionsClick(false)
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(text = stringResource(id = R.string.card_transactions))
            IconButton(
                onClick = {},
                enabled = false,
                content = {}
            )
        }
        LazyColumn {
            itemsIndexed(list) { index, transaction ->
                if (list.size == index + 1) {
                    onSelectChunkedListIndex((index * 2).div(19))
                }
                TransactionItem(
                    transaction = transaction
                ) {
                    onTransactionClick(transaction)
                }
            }
        }
    }
}