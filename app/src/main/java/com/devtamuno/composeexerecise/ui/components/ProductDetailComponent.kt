package com.devtamuno.composeexerecise.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.devtamuno.composeexerecise.R
import com.devtamuno.composeexerecise.ui.screen.state.ProductDetailState
import com.devtamuno.composeexerecise.ui.theme.ComposeExerciseTheme
import com.devtamuno.composeexerecise.ui.theme.appWhite


@Preview
@Composable
fun ProductDetailComponent(
    modifier: Modifier = Modifier,
    state: ProductDetailState = ProductDetailState(),
    onBackPressed: () -> Unit = {}
) {

    val scrollableState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollableState)
            .padding(
                top = ComposeExerciseTheme.dimens.standard125,
                bottom = ComposeExerciseTheme.dimens.standard125,
                start = ComposeExerciseTheme.dimens.standard75,
                end = ComposeExerciseTheme.dimens.standard75
            )
            .then(modifier)
    ) {

        NavBar(modifier = Modifier.fillMaxWidth(), onBackPressed = onBackPressed)

        Spacer(modifier = Modifier.padding(top = ComposeExerciseTheme.dimens.standard125))

        when {
            state.isLoading -> {
                LoadingView(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("loading_view")
                )
            }

            state.error != null -> {
                ErrorComponent(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("error_view"),
                    message = state.error.message ?: stringResource(
                        id = R.string.an_unknown_error_occured
                    )
                )
            }

            else -> {

                ProductImagePoster(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(2f),
                    posterUrl = state.productDetail?.images?.get(0) ?: ""
                )

                Text(
                    text = state.productDetail?.title.orEmpty(),
                    color = appWhite,
                    fontSize = 22.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = ComposeExerciseTheme.dimens.standard75)
                        .align(Alignment.CenterHorizontally)
                )


                Text(
                    text = stringResource(id = R.string.overview),
                    color = appWhite,
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = ComposeExerciseTheme.dimens.standard150)
                )
                Text(
                    text = state.productDetail?.description.orEmpty(),
                    color = appWhite,
                    fontSize = 15.sp,
                    softWrap = true,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier.align(Alignment.Start)
                )

            }
        }

    }

}

@Composable
private fun NavBar(modifier: Modifier, onBackPressed: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        IconButton(onClick = onBackPressed, modifier = Modifier.testTag("back_icon")) {
            Icon(
                painterResource(id = R.drawable.arrow_back),
                tint = appWhite,
                contentDescription = null,
            )
        }
        Text(
            text = stringResource(id = R.string.product_detail),
            color = appWhite,
            fontSize = 17.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .testTag("toolbar_title")
                .padding(start = ComposeExerciseTheme.dimens.standard150)
        )
    }

}


