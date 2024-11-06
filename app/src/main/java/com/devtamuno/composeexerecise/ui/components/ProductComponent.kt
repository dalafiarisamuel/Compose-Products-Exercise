package com.devtamuno.composeexerecise.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.devtamuno.composeexerecise.ui.data.ProductUi
import com.devtamuno.composeexerecise.ui.theme.ColorMatteBlack
import com.devtamuno.composeexerecise.ui.theme.ColorWhite
import com.devtamuno.composeexerecise.ui.theme.appDark
import com.devtamuno.composeexerecise.ui.theme.appWhite


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductComponent(
    modifier: Modifier,
    productUi: ProductUi?,
    onProductClicked: (ProductUi?) -> Unit,
) {

    val isShowProgress by remember { mutableStateOf(MutableTransitionState(true)) }
    val painter = rememberAsyncImagePainter(productUi?.thumbnail)

    when (painter.state) {
        is AsyncImagePainter.State.Loading,
        is AsyncImagePainter.State.Empty -> { /*default state*/
        }
        is AsyncImagePainter.State.Error,
        is AsyncImagePainter.State.Success -> {
            isShowProgress.targetState = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {

        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(onClick = { onProductClicked(productUi) })

        ) {
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
            )
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visibleState = isShowProgress
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.Center),
                color = ColorMatteBlack
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .background(
                    ColorMatteBlack,
                    shape = RoundedCornerShape(
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp
                    )
                )
                .align(Alignment.BottomCenter)
        ) {

            Text(
                text = productUi?.title.orEmpty(),
                color = appDark,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                softWrap = false,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp, top = 5.dp)
                    .basicMarquee()
            )

            Text(
                text = "${productUi?.brand.orEmpty()} • $${productUi?.price}",
                color = appDark,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                softWrap = false,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                    .basicMarquee()
            )

        }

    }
}