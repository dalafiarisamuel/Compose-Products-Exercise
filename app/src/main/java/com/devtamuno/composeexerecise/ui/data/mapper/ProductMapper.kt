package com.devtamuno.composeexerecise.ui.data.mapper

import com.devtamuno.composeexerecise.remote.data.ProductRemote
import com.devtamuno.composeexerecise.ui.data.ProductUi
import javax.inject.Inject

class ProductMapper @Inject constructor() : UiMapper<ProductRemote, ProductUi> {
    override fun mapToUi(input: ProductRemote): ProductUi {
        return with(input) {
            ProductUi(
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