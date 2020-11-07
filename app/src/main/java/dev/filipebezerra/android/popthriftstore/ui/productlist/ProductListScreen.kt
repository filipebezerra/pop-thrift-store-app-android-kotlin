package dev.filipebezerra.android.popthriftstore.ui.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.filipebezerra.android.popthriftstore.databinding.ProductListScreenBinding

class ProductListScreen : Fragment() {

    private val productListViewModel: ProductListViewModel by viewModels()

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
    }

    private fun setupProductAdapter() {
        ProductAdapter(productListViewModel).apply {
            productAdapter = this
            viewBinding.productList.adapter = productAdapter
        }
    }
}