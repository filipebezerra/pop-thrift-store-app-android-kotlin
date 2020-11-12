package dev.filipebezerra.android.popthriftstore.ui.productlist

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.filipebezerra.android.popthriftstore.MainActivityViewModel
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.databinding.ProductListScreenBinding
import dev.filipebezerra.android.popthriftstore.ui.badge.BadgeDrawable
import dev.filipebezerra.android.popthriftstore.ui.productlist.ProductListViewModel.Companion.provideFactory
import dev.filipebezerra.android.popthriftstore.util.event.EventObserver
import dev.filipebezerra.android.popthriftstore.ui.productlist.ProductListScreenDirections.Companion.actionProductListToLogin as toLogin
import dev.filipebezerra.android.popthriftstore.ui.productlist.ProductListScreenDirections.Companion.actionProductListToProductDetail as toProductDetail

class ProductListScreen : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels { MainActivityViewModel.provideFactory() }

    private val productListViewModel: ProductListViewModel by viewModels { provideFactory() }

    private val navController by lazy { findNavController() }

    private lateinit var viewBinding: ProductListScreenBinding

    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        productListViewModel.navigateToLogin.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(toLogin())
        })
        productListViewModel.navigateToProductDetail.observe(viewLifecycleOwner,
            EventObserver { productId ->
                navController.navigate(toProductDetail(productId))
            })
        productListViewModel.cart.observe(viewLifecycleOwner) { cart ->
            createOrUpdateCartBadge(cart.products.size)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    private var cartItemsCountBadge: BadgeDrawable? = null

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        checkForDisplayCartBadgeAt(menu)
    }

    private fun checkForDisplayCartBadgeAt(menu: Menu) {
        if (cartItemsCountBadge != null) {
            menu.findItem(R.id.my_cart_action).let { cartMenu ->
                (cartMenu.icon as LayerDrawable).apply {
                    mutate()
                    setDrawableByLayerId(R.id.ic_badge, cartItemsCountBadge)
                    cartMenu.icon = this
                    cartItemsCountBadge = null
                }
            }
        }
    }

    private fun createOrUpdateCartBadge(cartItemsCount: Int) {
        activity?.let {
            if (cartItemsCountBadge == null) {
                cartItemsCountBadge = BadgeDrawable.create(it)
                    .apply { count = cartItemsCount }
            } else {
                cartItemsCountBadge!!.count = cartItemsCount
            }
            it.invalidateOptionsMenu()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.logout_action -> {
            mainActivityViewModel.logout()
            true
        }
        R.id.my_cart_action -> {
            productListViewModel.showCart()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}