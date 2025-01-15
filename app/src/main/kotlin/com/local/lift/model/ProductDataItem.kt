package com.local.lift.model

data class ProductDataItem(
    val category_product: CategoryProduct,
    val product_detail: ProductDetail,
    val product_image: String,
    val product_name: String,
    val product_price: Double
)