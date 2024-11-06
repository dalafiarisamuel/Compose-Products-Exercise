package com.devtamuno.composeexerecise.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.devtamuno.composeexerecise.ui.data.ProductUi
import com.devtamuno.composeexerecise.ui.theme.ComposeExerciseTheme

@Composable
fun ProductList(
    modifier: Modifier,
    products: LazyPagingItems<ProductUi>,
    lazyListState: LazyGridState,
    onProductClicked: (ProductUi?) -> Unit
) {

    val isListEmpty by remember { derivedStateOf { products.itemCount <= 0 } }

    if (products.loadState.refresh is LoadState.Loading) {
        LoadingView(
            modifier = Modifier
                .fillMaxSize()
                .testTag("loading_indicator")
        )
    } else {
        Crossfade(targetState = isListEmpty) {
            if (it) {
                EmptyListStateComponent(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("empty_list_state_indicator")
                )
            } else {
                ProductsGridComponent(
                    modifier = modifier,
                    productUiList = products,
                    lazyListState = lazyListState,
                    onProductClicked = onProductClicked
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProductsGridComponent(
    modifier: Modifier,
    productUiList: LazyPagingItems<ProductUi>,
    lazyListState: LazyGridState,
    onProductClicked: (ProductUi?) -> Unit,
) {
    LazyVerticalGrid(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(ComposeExerciseTheme.dimens.standard50),
        horizontalArrangement = Arrangement.spacedBy(ComposeExerciseTheme.dimens.standard50),
        modifier = Modifier
            .testTag("productLazyList")
            .padding(
                top = ComposeExerciseTheme.dimens.standard75,
            )
            .then(modifier),
        columns = GridCells.Fixed(2),
        content = {
            items(productUiList) { product ->
                ProductComponent(
                    modifier = Modifier.animateItemPlacement(),
                    productUi = product,
                    onProductClicked = onProductClicked
                )
            }
        }
    )
}

private fun <T : Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyGridItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}