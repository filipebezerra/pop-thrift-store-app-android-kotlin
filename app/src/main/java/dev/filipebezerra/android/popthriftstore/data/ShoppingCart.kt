package dev.filipebezerra.android.popthriftstore.data

data class ShoppingCart(
    var user: User? = null,
    val products: MutableSet<Product> = mutableSetOf(),
)