package dev.filipebezerra.android.popthriftstore.ui.productdetail

import androidx.lifecycle.*
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.ServiceLocator
import dev.filipebezerra.android.popthriftstore.data.Product
import dev.filipebezerra.android.popthriftstore.data.ProductRepository
import dev.filipebezerra.android.popthriftstore.data.ShoppingCartRepository
import dev.filipebezerra.android.popthriftstore.util.event.Event
import dev.filipebezerra.android.popthriftstore.util.ext.postEvent
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productId: Long,
    private val productRepository: ProductRepository,
    private val shoppingCartRepository: ShoppingCartRepository,
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product

    private val _messaging = MutableLiveData<Event<Int>>()
    val messaging: LiveData<Event<Int>>
        get() = _messaging

    val productIsInCart: LiveData<Boolean> = Transformations.map(_product) {
        shoppingCartRepository.checkIfProductIsInCart(it)
    }

    init {
        loadProduct()
    }

    private fun loadProduct() {
        viewModelScope.launch {
            _product.value = productRepository.findProductById(productId)
        }
    }

    fun addToCart() {
        _product.value?.let {
            if (productIsInCart.value!!) {
                _messaging.postEvent(R.string.product_already_in_cart)
                return
            }
            _product.value = shoppingCartRepository.addToShoppingCart(it)
            _messaging.postEvent(R.string.added_to_cart)
        }
    }

    fun addToWishList() {
        _messaging.postEvent(R.string.added_to_wish_list)
    }

    fun shareProduct() {

    }

    companion object {
        fun provideFactory(
            productId: Long,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                ProductDetailViewModel(
                    productId,
                    ServiceLocator.provideProductRepository(),
                    ServiceLocator.provideShoppingCartRepository(),
                ) as T
        }
    }
}