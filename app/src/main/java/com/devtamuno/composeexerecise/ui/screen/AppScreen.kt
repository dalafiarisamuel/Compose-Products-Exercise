package com.devtamuno.composeexerecise.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devtamuno.composeexerecise.ui.navigation.ARG_PRODUCT_ID
import com.devtamuno.composeexerecise.ui.navigation.ProductsScreen
import com.devtamuno.composeexerecise.ui.theme.LocalNavController

@Composable
fun AppScreen() {

    val navController = LocalNavController.current

    NavHost(navController = navController, startDestination = ProductsScreen.PRODUCT_LIST.route) {

        composable(ProductsScreen.PRODUCT_LIST.route) { ProductListScreen() }

        composable(
            route = ProductsScreen.PRODUCT_DETAIL.route,
            arguments = listOf(navArgument(ARG_PRODUCT_ID) { type = NavType.IntType }),
            content = { ProductDetailScreen() }
        )

    }
}