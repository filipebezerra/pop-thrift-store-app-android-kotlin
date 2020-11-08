package dev.filipebezerra.android.popthriftstore.ui.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dev.filipebezerra.android.popthriftstore.databinding.ProductDetailScreenBinding
import dev.filipebezerra.android.popthriftstore.ui.productdetail.ProductDetailViewModel.Companion.provideFactory
import dev.filipebezerra.android.popthriftstore.ui.util.setupSnackbar

class ProductDetailScreen : Fragment() {

    private val args: ProductDetailScreenArgs by navArgs()

    private val productDetailViewModel: ProductDetailViewModel by viewModels { provideFactory(args.productId) }

    private lateinit var viewBinding: ProductDetailScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ProductDetailScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            viewModel = productDetailViewModel
        }
        .root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        view?.setupSnackbar(viewLifecycleOwner, productDetailViewModel.messaging, Snackbar.LENGTH_SHORT)
    }
}