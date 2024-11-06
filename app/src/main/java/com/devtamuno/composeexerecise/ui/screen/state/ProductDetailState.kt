package com.devtamuno.composeexerecise.ui.screen.state


import com.devtamuno.composeexerecise.ui.data.ProductDetail

data class ProductDetailState(
    val isLoading: Boolean = true,
    val productDetail: ProductDetail? = null,
    val error: Throwable? = null
)
