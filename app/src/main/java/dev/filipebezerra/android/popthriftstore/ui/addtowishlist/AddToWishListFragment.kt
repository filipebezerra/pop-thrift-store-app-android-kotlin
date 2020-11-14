package dev.filipebezerra.android.popthriftstore.ui.addtowishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.filipebezerra.android.popthriftstore.databinding.AddToWishListFragmentBinding
import dev.filipebezerra.android.popthriftstore.util.event.EventObserver

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
        observeUi()
    }

    private fun observeUi() {
        addToWishListViewModel.productAdded.observe(viewLifecycleOwner,
            EventObserver { message ->
                context?.let { context ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT)
                        .show();
                }
                this@AddToWishListFragment.dismiss()
            })
    }
}