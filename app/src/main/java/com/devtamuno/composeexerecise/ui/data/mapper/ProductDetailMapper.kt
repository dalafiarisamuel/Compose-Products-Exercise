package com.devtamuno.composeexerecise.ui.data.mapper

import com.devtamuno.composeexerecise.remote.data.ProductRemoteResponse
import com.devtamuno.composeexerecise.ui.data.ProductDetail
import javax.inject.Inject

class ProductDetailMapper @Inject constructor() : UiMapper<ProductRemoteResponse, ProductDetail> {

    override fun mapToUi(input: ProductRemoteResponse): ProductDetail {
        return with(input) {

            ProductDetail(
                id = id,
                title = title,
                description = description,
                category = category,
                price = price,
                discountPercentage = discountPercentage,
                rating = rating,
                stock = stock,
                tags = tags,
                brand = brand.orEmpty(),
                sku = sku,
                weight = weight,
                warrantyInformation = warrantyInformation,
                shippingInformation = shippingInformation,
                availabilityStatus = availabilityStatus,
                returnPolicy = returnPolicy,
                minimumOrderQuantity = minimumOrderQuantity,
                images = images,
                thumbnail = thumbnail
            )
        }
    }
}