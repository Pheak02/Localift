package com.local.lift.model

data class HomepageData(
    val categoryProductHome: CategoryProductHome,
    val productDetailHome: ProductDetailHome,
)

data class CategoryProductHome(
    val categoryName: String,
    val categoryImage: String?
)

data class ProductDetailHome(
    val productImage: String?,
    val productName: String,
    val productPrice: Double
)