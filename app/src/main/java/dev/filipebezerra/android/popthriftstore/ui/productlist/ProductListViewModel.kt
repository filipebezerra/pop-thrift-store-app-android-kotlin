package dev.filipebezerra.android.popthriftstore.ui.productlist

import androidx.lifecycle.*
import dev.filipebezerra.android.popthriftstore.ServiceLocator
import dev.filipebezerra.android.popthriftstore.data.*
import dev.filipebezerra.android.popthriftstore.util.event.Event
import dev.filipebezerra.android.popthriftstore.util.ext.postEvent
import kotlinx.coroutines.launch

class ProductListViewModel(
    userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val shoppingCartRepository: ShoppingCartRepository,
) : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    private val _cart = MutableLiveData<ShoppingCart>()
    val cart: LiveData<ShoppingCart>
        get() = _cart

    private val _navigateToProductDetail = MutableLiveData<Event<Long>>()
    val navigateToProductDetail: LiveData<Event<Long>>
        get() = _navigateToProductDetail

    private val _navigateToLogin = MutableLiveData<Event<Any>>()
    val navigateToLogin: LiveData<Event<Any>>
        get() = _navigateToLogin

    init {
        if (userRepository.isUserLoggedIn()) {
            loadProductList()
            loadCart()
        } else {
            signInUser()
        }
    }

    private fun loadProductList() {
        viewModelScope.launch {
            _productList.value = productRepository.listProducts()
        }
    }

    private fun loadCart() {
        viewModelScope.launch {
            _cart.value = shoppingCartRepository.getCart()
        }
    }

    private fun signInUser() {
        _navigateToLogin.postEvent(true)
    }

    fun showProductDetail(productId: Long) {
        _navigateToProductDetail.postEvent(productId)
    }

    fun showCart() {
        // TODO notify observers to show the cart
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                ProductListViewModel(
                    ServiceLocator.provideUserRepository(),
                    ServiceLocator.provideProductRepository(),
                    ServiceLocator.provideShoppingCartRepository(),
                ) as T
        }
    }
}

