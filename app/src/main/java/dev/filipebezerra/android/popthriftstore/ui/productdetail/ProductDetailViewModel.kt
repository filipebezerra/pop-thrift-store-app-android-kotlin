package dev.filipebezerra.android.popthriftstore.ui.productdetail

import androidx.lifecycle.*
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.ServiceLocator
import dev.filipebezerra.android.popthriftstore.data.Product
import dev.filipebezerra.android.popthriftstore.data.ProductRepository
import dev.filipebezerra.android.popthriftstore.util.event.Event
import dev.filipebezerra.android.popthriftstore.util.ext.postEvent
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productRepository: ProductRepository,
    private val productId: Long,
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product

    private val _messaging = MutableLiveData<Event<Int>>()
    val messaging: LiveData<Event<Int>>
        get() = _messaging

    private val _navigateBack = MutableLiveData<Event<Boolean>>()
    val navigateBack: LiveData<Event<Boolean>>
        get() = _navigateBack

    init {
        loadProduct()
    }

    private fun loadProduct() {
        viewModelScope.launch {
            _product.value = productRepository.findProductById(productId)
        }
    }

    fun addToCart() {
        _messaging.postEvent(R.string.added_to_cart)
//        _navigateBack.postEvent(true)
    }

    fun addToWishList() {
        _messaging.postEvent(R.string.added_to_wish_list)
//        _navigateBack.postEvent(true)
    }

    companion object {
        fun provideFactory(
            productId: Long,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                ProductDetailViewModel(
                    ServiceLocator.provideProductRepository(),
                    productId,
                ) as T
        }
    }
}