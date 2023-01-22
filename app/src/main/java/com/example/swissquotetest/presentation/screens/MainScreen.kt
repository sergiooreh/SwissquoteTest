package com.example.swissquotetest.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.swissquotetest.R
import com.example.swissquotetest.domain.models.Transaction
import com.example.swissquotetest.presentation.composables.*
import com.example.swissquotetest.presentation.state.MainScreenUiState
import com.example.swissquotetest.presentation.util.Args
import com.example.swissquotetest.presentation.util.Route
import com.example.swissquotetest.presentation.viewmodel.BankViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@Composable
fun MainRoute(
   navController: NavController,
   viewModel: BankViewModel = hiltViewModel()
) {
   val uiState by viewModel.uiState.collectAsState()

   MainScreen(
      uiState = uiState,
      onShowAllTransactionsClick = viewModel::changeShowAllTransactions,
      onSelectedCardChanged = viewModel::changeSelectedCard,
      onSelectChunkedListIndex = viewModel::changeChunkedListIndex,
      onTransactionClick = { transaction ->
         navController.currentBackStackEntry?.savedStateHandle?.set(
            key = Args.transaction,
            value = transaction
         )
         navController.navigate(Route.TRANSACTION_DETAIL)
      }
   )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
   uiState: MainScreenUiState,
   onShowAllTransactionsClick: (Boolean) -> Unit,
   onSelectedCardChanged: (Int) -> Unit,
   onSelectChunkedListIndex: (Int) -> Unit,
   onTransactionClick: (Transaction) -> Unit
) {
   val scaffoldState = rememberScaffoldState()
   val context = LocalContext.current
   LaunchedEffect(key1 = uiState.errorMessage) {
      if (!uiState.errorMessage.isNullOrEmpty()) {
         Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
      }
   }
   Scaffold(
      modifier = Modifier.fillMaxSize(),
      scaffoldState = scaffoldState
   ) { paddingValues ->
      if (uiState.showAllTransactions) {
         val chunkedList = (uiState.pendingTransactions + uiState.executedTransactions).chunked(20)
         val selectedLists = chunkedList.take(uiState.currentChunkedListIndex)
         val list = selectedLists.flatten()
         TransactionListComposable(
            list,
            onSelectChunkedListIndex,
            onShowAllTransactionsClick,
            onTransactionClick
         )
      } else Column(
         horizontalAlignment = Alignment.CenterHorizontally,
         modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(8.dp)
      ) {
         HorizontalPager(
            count = uiState.cards.size,
            contentPadding = PaddingValues(16.dp)
         ) {index ->
            onSelectedCardChanged(index)
            BankCard(
               lastFourDigits = uiState.cards[index].numbers.takeLast(4)
            )
         }
         AvailableAmount(
            sum = uiState.cards.getOrNull(uiState.selectedCard)?.amount ?: "0.00",
            currency = uiState.cards.getOrNull(uiState.selectedCard)?.currency ?: "CHF"
         )
         Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
               .fillMaxWidth()
         ) {
            MenuButton(
               imageId = R.drawable.padlock,
               titleId = R.string.lock_card
            ) {
               Toast.makeText(context, R.string.lock_card, Toast.LENGTH_SHORT).show()
            }
            MenuButton(
               imageId = R.drawable.setting,
               titleId = R.string.settings
            ) {
               Toast.makeText(context, R.string.settings, Toast.LENGTH_SHORT).show()
            }
         }
         Spacer(modifier = Modifier.height(8.dp))
         CardTransactions(
            transactions = uiState.pendingTransactions + uiState.executedTransactions,
            modifier = Modifier,
            onViewAllClick = {
               onShowAllTransactionsClick(true)
            }
         ) { transaction ->
            onTransactionClick(transaction)
         }
      }
   }
   Loader(isVisible = uiState.isLoading)
}