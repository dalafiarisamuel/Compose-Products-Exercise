package com.devtamuno.composeexerecise.remote.repository

import com.devtamuno.composeexerecise.remote.data.ProductRemoteResponse
import com.devtamuno.composeexerecise.remote.data.ProductsRemoteResponse
import com.devtamuno.composeexerecise.remote.service.ProductService

class ProductsRemoteRepositoryImpl(private val api: ProductService) : ProductsRemoteRepository {

    override suspend fun getAllProducts(limit: Int, skip: Int): Resource<ProductsRemoteResponse> {
        return resourceHelper { api.getAllProducts(limit, skip) }
    }

    override suspend fun getProductById(productId: Int): Resource<ProductRemoteResponse> {
        return resourceHelper { api.getProductById(productId) }
    }
}