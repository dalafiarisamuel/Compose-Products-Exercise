package com.devtamuno.composeexerecise.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.devtamuno.composeexerecise.ui.components.ProductListComponent
import com.devtamuno.composeexerecise.ui.navigation.ProductsScreen
import com.devtamuno.composeexerecise.ui.theme.LocalNavController
import com.devtamuno.composeexerecise.ui.viewmodel.ProductListViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductListScreen() {
    val viewModel = hiltViewModel<ProductListViewModel>()
    val navController = LocalNavController.current

    ProductListComponent(
        modifier = Modifier.fillMaxSize()
            .padding(),
        products = viewModel.products,
    ) { product ->
        product?.let {
            navController.navigate(
                ProductsScreen.PRODUCT_DETAIL.createPath(product.id)
            )
        }

    }

}