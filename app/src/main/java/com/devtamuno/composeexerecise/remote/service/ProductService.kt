package com.devtamuno.composeexerecise.remote.service

import com.devtamuno.composeexerecise.remote.data.ProductRemoteResponse
import com.devtamuno.composeexerecise.remote.data.ProductsRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products")
    suspend fun getAllProducts(): ProductsRemoteResponse

    @GET("products/{product_id}")
    suspend fun getProductById(
        @Path("product_id") productId: Int
    ): ProductRemoteResponse


}
