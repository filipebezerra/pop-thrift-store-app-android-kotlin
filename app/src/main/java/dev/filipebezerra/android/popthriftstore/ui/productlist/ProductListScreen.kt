package dev.filipebezerra.android.popthriftstore.ui.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.filipebezerra.android.popthriftstore.databinding.ProductListScreenBinding
import dev.filipebezerra.android.popthriftstore.ui.productlist.ProductListViewModel.Companion.provideFactory
import dev.filipebezerra.android.popthriftstore.util.event.EventObserver
import dev.filipebezerra.android.popthriftstore.ui.productlist.ProductListScreenDirections.Companion.actionProductListToProductDetail as toProductDetail

class ProductListScreen : Fragment() {

    private val productListViewModel: ProductListViewModel by viewModels { provideFactory() }

    private val navController by lazy { findNavController() }

    private lateinit var viewBinding: ProductListScreenBinding

    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ProductListScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            viewModel = productListViewModel
        }
        .root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        setupProductAdapter()
        observeUi()
    }

    private fun setupProductAdapter() {
        ProductAdapter(productListViewModel).apply {
            productAdapter = this
            viewBinding.productList.adapter = productAdapter
        }
    }

    private fun observeUi() {
        productListViewModel.navigateToProductDetail.observe(viewLifecycleOwner,
            EventObserver { productId ->
                navController.navigate(toProductDetail(productId))
            })
    }
}