package com.devtamuno.composeexerecise.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.devtamuno.composeexerecise.R
import com.devtamuno.composeexerecise.ui.data.ProductUi
import com.devtamuno.composeexerecise.ui.theme.ComposeExerciseTheme
import com.devtamuno.composeexerecise.ui.theme.appWhite
import kotlinx.coroutines.flow.Flow

@Composable
fun ProductListComponent(
    modifier: Modifier,
    products: Flow<PagingData<ProductUi>>,
    onProductClicked: (ProductUi?) -> Unit
) {

    val listScrollState = rememberLazyGridState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = ComposeExerciseTheme.dimens.standard50,
                end = ComposeExerciseTheme.dimens.standard50,
            )
    ) {

        Spacer(modifier = Modifier.padding(top = ComposeExerciseTheme.dimens.standard125))

        Text(
            text = stringResource(id = R.string.app_name),
            color = appWhite,
            fontSize = 22.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 5.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        ProductList(
            modifier = Modifier,
            products = products.collectAsLazyPagingItems(),
            lazyListState = listScrollState,
            onProductClicked = onProductClicked,
        )
    }

}