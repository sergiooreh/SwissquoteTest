package com.example.swissquotetest.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.swissquotetest.domain.models.Transaction
import com.example.swissquotetest.presentation.screens.MainRoute
import com.example.swissquotetest.presentation.screens.TransactionDetailRoute
import com.example.swissquotetest.presentation.util.Args
import com.example.swissquotetest.presentation.util.Route


@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.MAIN) {
        composable(Route.MAIN) {
            MainRoute(
                navController = navController
            )
        }
        composable(Route.TRANSACTION_DETAIL) {
            val transaction = navController.previousBackStackEntry?.savedStateHandle
                ?.get<Transaction>(Args.transaction)
            TransactionDetailRoute(
                navController = navController,
                transaction ?: return@composable
            )
        }
    }
}