package com.devtamuno.composeexerecise.remote.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsRemoteResponse(@SerialName("products") val products: List<ProductRemote>)
