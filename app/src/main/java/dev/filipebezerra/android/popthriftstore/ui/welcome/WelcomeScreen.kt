package dev.filipebezerra.android.popthriftstore.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.filipebezerra.android.popthriftstore.databinding.WelcomeScreenBinding
import dev.filipebezerra.android.popthriftstore.ui.welcome.WelcomeScreenDirections.Companion.actionWelcomeScreenToInstructionScreen as toInstruction

class WelcomeScreen : Fragment() {

    private val welcomeViewModel: WelcomeViewModel by viewModels()

    private val navController by lazy { findNavController() }

    private lateinit var viewBinding: WelcomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = WelcomeScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = welcomeViewModel
        }
        .root


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observerUi()
    }

    private fun observerUi() {
        welcomeViewModel.navigateToInstruction.observe(viewLifecycleOwner) {
            it?.let {
                navController.navigate(toInstruction())
                welcomeViewModel.doneNavigationToInstructions()
            }
        }
    }
}