package com.devtamuno.composeexerecise.remote.repository

import com.devtamuno.composeexerecise.remote.data.ProductRemoteResponse
import com.devtamuno.composeexerecise.remote.data.ProductsRemoteResponse
import com.devtamuno.composeexerecise.remote.service.ProductService

class ProductsRemoteRepositoryImpl(private val api: ProductService) : ProductsRemoteRepository {

    override suspend fun getAllProducts(): Resource<ProductsRemoteResponse> {
        return resourceHelper { api.getAllProducts() }
    }

    override suspend fun getProductById(productId: Int): Resource<ProductRemoteResponse> {
        return resourceHelper { api.getProductById(productId) }
    }
}