package com.devtamuno.composeexerecise.remote.service

import com.devtamuno.composeexerecise.remote.data.ProductRemoteResponse
import com.devtamuno.composeexerecise.remote.data.ProductsRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("products")
    suspend fun getAllProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): ProductsRemoteResponse

    @GET("products/{product_id}")
    suspend fun getProductById(
        @Path("product_id") productId: Int
    ): ProductRemoteResponse


}
