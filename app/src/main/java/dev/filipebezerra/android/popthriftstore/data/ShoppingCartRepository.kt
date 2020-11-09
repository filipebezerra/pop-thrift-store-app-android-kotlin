package dev.filipebezerra.android.popthriftstore.data

class ShoppingCartRepository(
    private val userRepository: UserRepository
) {

    companion object {
        @Volatile
        private var INSTANCE: ShoppingCart? = null

        private fun getCart(
            userRepository: UserRepository
        ): ShoppingCart = INSTANCE ?: synchronized(this) {
            val currentUser =
                userRepository.getCurrentUser()
                    ?: throw IllegalStateException("User must be logged in")
            val cart = ShoppingCart(user = currentUser)
            INSTANCE = cart
            cart
        }
    }

    fun addToShoppingCart(product: Product) {
        getCart(userRepository)
            .takeUnless { it.products.contains(product) }
            .apply {
                if (this == null)
                    throw IllegalArgumentException("Product is already in cart")
                this.products.add(product)
            }
    }

    fun getCart(): ShoppingCart = getCart(userRepository)

    fun endSession() {
        getCart(userRepository).apply {
            user = null
            products.clear()
            userRepository.signOutUser()
        }
    }
}