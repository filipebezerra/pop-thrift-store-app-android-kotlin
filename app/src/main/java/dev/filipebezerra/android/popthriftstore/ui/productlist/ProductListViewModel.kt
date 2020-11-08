package dev.filipebezerra.android.popthriftstore.ui.productlist

import androidx.lifecycle.*
import dev.filipebezerra.android.popthriftstore.ServiceLocator
import dev.filipebezerra.android.popthriftstore.data.Product
import dev.filipebezerra.android.popthriftstore.data.ProductRepository
import dev.filipebezerra.android.popthriftstore.util.event.Event
import dev.filipebezerra.android.popthriftstore.util.ext.postEvent
import kotlinx.coroutines.launch
class ProductListViewModel(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    private val _navigateToProductDetail = MutableLiveData<Event<Long>>()
    val navigateToProductDetail: LiveData<Event<Long>>
        get() = _navigateToProductDetail

    init {
        loadProductList()
    }

    private fun loadProductList() {
        viewModelScope.launch {
            _productList.value = productRepository.listProducts()
        }
    }

    fun showProductDetail(productId: Long) {
        _navigateToProductDetail.postEvent(productId)
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                ProductListViewModel(
                    ServiceLocator.provideProductRepository(),
                ) as T
        }
    }
}

