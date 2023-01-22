package com.example.swissquotetest.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swissquotetest.R
import com.example.swissquotetest.domain.models.Transaction
import com.example.swissquotetest.presentation.composables.RoundedIcon

@Composable
fun TransactionDetailRoute(
    navController: NavController,
    transaction: Transaction
) {

    TransactionDetailScreen(
        onNavigateBack = { navController.navigateUp() },
        transaction = transaction
    )
}


@Composable
fun TransactionDetailScreen(
    onNavigateBack: () -> Unit,
    transaction: Transaction
) {
    Scaffold() { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                IconButton(onClick = {
                    onNavigateBack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Text(text = stringResource(id = R.string.transaction_detail))
                IconButton(
                    onClick = {},
                    enabled = false,
                    content = {}
                )
            }
            RoundedIcon(image = transaction.category.image)
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                shape = RoundedCornerShape(10.dp, 10.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = transaction.date,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "-${transaction.amount} ${transaction.currency}",
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = transaction.merchand,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}