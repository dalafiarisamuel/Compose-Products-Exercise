package com.devtamuno.composeexerecise.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devtamuno.composeexerecise.ui.components.ProductDetailComponent
import com.devtamuno.composeexerecise.ui.theme.LocalNavController
import com.devtamuno.composeexerecise.ui.viewmodel.ProductDetailViewModel

@Composable
fun ProductDetailScreen() {

    val viewModel = hiltViewModel<ProductDetailViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val navController = LocalNavController.current

    ProductDetailComponent(modifier = Modifier.fillMaxSize(), state = state) {
        navController.popBackStack()
    }

}