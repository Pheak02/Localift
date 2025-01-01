package com.local.lift.model

data class Order(
    val date: String,
    val billingName: String,
    val amount: String,
    val status: String,
    val orderNumber: String
)
