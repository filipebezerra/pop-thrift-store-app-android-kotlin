package dev.filipebezerra.android.popthriftstore.ui.addtowishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dev.filipebezerra.android.popthriftstore.databinding.AddToWishListFragmentBinding
import dev.filipebezerra.android.popthriftstore.ui.util.setupSnackbar

class AddToWishListFragment : BottomSheetDialogFragment() {

    private val addToWishListViewModel: AddToWishListViewModel by viewModels()

    private lateinit var viewBinding: AddToWishListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = AddToWishListFragmentBinding.inflate(inflater)
        .apply {
            viewBinding = this
            viewModel = addToWishListViewModel
        }
        .root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        setupSnackbar()
    }

    private fun setupSnackbar() =
        view?.setupSnackbar(
            viewLifecycleOwner,
            addToWishListViewModel.messaging,
            Snackbar.LENGTH_SHORT,
            anchorView = viewBinding.root,
            onDismiss = { this@AddToWishListFragment.dismiss() }
        )
}