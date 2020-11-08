package dev.filipebezerra.android.popthriftstore

import dev.filipebezerra.android.popthriftstore.data.ProductRepository

object ServiceLocator {

    @Volatile
    private var productRepository: ProductRepository? = null

    fun provideProductRepository(): ProductRepository {
        synchronized(this) {
            return  productRepository ?: productRepository ?: ProductRepository()
        }
    }
}