package com.devtamuno.composeexerecise.ui.data.mapper

import com.devtamuno.composeexerecise.remote.data.ProductsRemoteResponse
import com.devtamuno.composeexerecise.ui.data.ProductsUi
import javax.inject.Inject

class ProductsMapper @Inject constructor(private val productMapper: ProductMapper) :
    UiMapper<ProductsRemoteResponse, ProductsUi> {
    override fun mapToUi(input: ProductsRemoteResponse): ProductsUi {
        return with(input) {
            ProductsUi(productUis = products.map(productMapper::mapToUi))
        }
    }
}