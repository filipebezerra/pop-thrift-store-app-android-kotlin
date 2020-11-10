package dev.filipebezerra.android.popthriftstore.ui.productlist

import androidx.lifecycle.*
import dev.filipebezerra.android.popthriftstore.ServiceLocator
import dev.filipebezerra.android.popthriftstore.data.Product
import dev.filipebezerra.android.popthriftstore.data.ProductRepository
import dev.filipebezerra.android.popthriftstore.data.UserRepository
import dev.filipebezerra.android.popthriftstore.util.event.Event
import dev.filipebezerra.android.popthriftstore.util.ext.postEvent
import kotlinx.coroutines.launch
class ProductListViewModel(
    userRepository: UserRepository,
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    private val _navigateToProductDetail = MutableLiveData<Event<Long>>()
    val navigateToProductDetail: LiveData<Event<Long>>
        get() = _navigateToProductDetail

    private val _navigateToLogin = MutableLiveData<Event<Any>>()
    val navigateToLogin: LiveData<Event<Any>>
        get() = _navigateToLogin

    init {
        if (userRepository.isUserLoggedIn()) {
            loadProductList()
        } else {
            signInUser()
        }
    }

    private fun loadProductList() {
        viewModelScope.launch {
            _productList.value = productRepository.listProducts()
        }
    }

    private fun signInUser() {
        _navigateToLogin.postEvent(true)
    }

    fun showProductDetail(productId: Long) {
        _navigateToProductDetail.postEvent(productId)
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                ProductListViewModel(
                    ServiceLocator.provideUserRepository(),
                    ServiceLocator.provideProductRepository(),
                ) as T
        }
    }
}

