package dev.filipebezerra.android.popthriftstore.ui.productdetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.databinding.ProductDetailScreenBinding
import dev.filipebezerra.android.popthriftstore.ui.productdetail.ProductDetailViewModel.Companion.provideFactory
import dev.filipebezerra.android.popthriftstore.ui.util.setupSnackbar
import dev.filipebezerra.android.popthriftstore.util.event.EventObserver
import dev.filipebezerra.android.popthriftstore.ui.productdetail.ProductDetailScreenDirections.Companion.actionProductDetailToAddToWishList as toAddToWishList

class ProductDetailScreen : Fragment() {

    private val args: ProductDetailScreenArgs by navArgs()

    private val productDetailViewModel: ProductDetailViewModel by viewModels { provideFactory(args.productId) }

    private lateinit var viewBinding: ProductDetailScreenBinding

    private val navController: NavController by lazy { findNavController() }

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
        setupSnackbar()
        observeUi()
    }

    private fun setupSnackbar() =
        view?.setupSnackbar(
            viewLifecycleOwner,
            productDetailViewModel.messaging,
            Snackbar.LENGTH_SHORT
        )

    private fun observeUi() {
        productDetailViewModel.product.observe(viewLifecycleOwner) { setActionBarTitle(it.name) }
        productDetailViewModel.shareProduct.observe(viewLifecycleOwner, EventObserver {
            createShareIntent()
        })
        productDetailViewModel.navigateToAddToWishList.observe(viewLifecycleOwner,
            EventObserver {
                navController.navigate(toAddToWishList())
            })
    }

    private fun createShareIntent() {
        productDetailViewModel.product.value?.let { product ->
            val shareText = product.discountedPrice.let { discountedPrice ->
                if (discountedPrice == null) {
                    getString(R.string.share_product_text, product.name, product.price)
                } else {
                    getString(R.string.share_discounted_product_text, product.name, discountedPrice)
                }
            }
            val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                .setText(shareText)
                .setType("text/plain")
                .createChooserIntent()
                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            startActivity(shareIntent)
        }
    }

    private fun setActionBarTitle(title: String) =
        title.let { (requireActivity() as AppCompatActivity).supportActionBar?.title = title }
}