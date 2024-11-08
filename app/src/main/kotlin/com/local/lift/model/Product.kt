package com.local.lift.model

class Product {
    data class Product(
        val id: Int,
        val name: String,
        val imageUrl: String,
        val price: Double
    )
}