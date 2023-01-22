package com.example.swissquotetest.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.swissquotetest.domain.models.enums.Status
import com.example.swissquotetest.domain.models.Transaction
import com.example.swissquotetest.presentation.ui.theme.LightGrey

@Composable
fun TransactionItem(
    transaction: Transaction,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedIcon(
                image = transaction.category.image,
                backgroundColor = LightGrey
            )
            Text(
                text = transaction.merchand,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.End) {
                Row() {
                    Text(text = "-${transaction.amount}")
                    Text(text = transaction.currency)
                }
                Text(
                    text = transaction.status.status,
                    fontWeight = if (transaction.status == Status.EXECUTED) FontWeight.Bold else FontWeight.Normal
                )
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Open",
                tint = Color.DarkGray,
                modifier = Modifier
                    .padding(start = 6.dp)
            )
        }
    }
}