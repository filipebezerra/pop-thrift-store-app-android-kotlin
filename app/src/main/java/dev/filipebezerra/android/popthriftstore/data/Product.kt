package dev.filipebezerra.android.popthriftstore.data

data class Product(
    val id: Long,
    val name: String,
    val sellerName: String,
    val imageUrl: String,
    val price: Double,
    val discountedPrice: Double?,
    val rating: Float
)