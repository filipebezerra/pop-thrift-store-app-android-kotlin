package dev.filipebezerra.android.popthriftstore.ui.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.filipebezerra.android.popthriftstore.databinding.ProductListScreenBinding

class ProductDetailScreen : Fragment() {


    private val viewModel: ProductDetailViewModel by viewModels()

    private val navController by lazy { findNavController() }

    private lateinit var viewBinding: ProductListScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ProductListScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            lifecycleOwner = viewLifecycleOwner
        }
        .root
}