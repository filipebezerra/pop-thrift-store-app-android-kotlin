package dev.filipebezerra.android.popthriftstore

import dev.filipebezerra.android.popthriftstore.data.ProductRepository
import dev.filipebezerra.android.popthriftstore.data.ShoppingCartRepository
import dev.filipebezerra.android.popthriftstore.data.UserRepository

object ServiceLocator {

    @Volatile
    private var productRepository: ProductRepository? = null

    @Volatile
    private var userRepository: UserRepository? = null

    @Volatile
    private var shoppingCartRepository: ShoppingCartRepository? = null

    fun provideProductRepository(): ProductRepository = synchronized(this) {
        return productRepository ?: ProductRepository()
    }

    fun provideUserRepository(): UserRepository = synchronized(this) {
        return userRepository ?: UserRepository()
    }

    fun provideShoppingCartRepository(): ShoppingCartRepository = synchronized(this) {
        return shoppingCartRepository ?: ShoppingCartRepository(provideUserRepository())
    }
}