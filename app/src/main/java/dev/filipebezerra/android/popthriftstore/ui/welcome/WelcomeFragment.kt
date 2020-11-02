package dev.filipebezerra.android.popthriftstore.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.databinding.WelcomeFragmentBinding
import dev.filipebezerra.android.popthriftstore.ui.welcome.WelcomeFragmentDirections.Companion.actionWelcomeFragmentToInstructionFragment as toInstruction

class WelcomeFragment : Fragment() {

    private val welcomeViewModel: WelcomeViewModel by viewModels()

    private val navController by lazy { findNavController() }

    private lateinit var viewBinding: WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = WelcomeFragmentBinding.inflate(inflater)
        .apply {
            viewBinding = this
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = welcomeViewModel
        }
        .root


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().findViewById<FloatingActionButton>(R.id.fab).apply {
            visibility = View.GONE
        }
        observerUi()
    }

    private fun observerUi() {
        welcomeViewModel.navigateToInstruction.observe(viewLifecycleOwner) {
            it?.let {
                navController.navigate(toInstruction())
                welcomeViewModel.onInstructionNavigated()
            }
        }
    }
}