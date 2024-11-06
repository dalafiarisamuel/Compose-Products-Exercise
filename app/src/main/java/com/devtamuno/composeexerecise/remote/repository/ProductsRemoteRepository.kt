package com.devtamuno.composeexerecise.remote.repository

import com.devtamuno.composeexerecise.remote.data.ProductRemoteResponse
import com.devtamuno.composeexerecise.remote.data.ProductsRemoteResponse

interface ProductsRemoteRepository {

    suspend fun getAllProducts(limit: Int, skip: Int): Resource<ProductsRemoteResponse>

    suspend fun getProductById(productId: Int): Resource<ProductRemoteResponse>
}