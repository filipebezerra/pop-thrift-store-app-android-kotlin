package dev.filipebezerra.android.popthriftstore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.databinding.LoginFragmentBinding
import dev.filipebezerra.android.popthriftstore.ui.login.LoginScreenDirections.Companion.actionLoginScreenToWelcomeScreen as toWelcomeFragment

class LoginScreen : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()

    private val navController by lazy { findNavController() }

    private lateinit var viewBinding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = LoginFragmentBinding.inflate(inflater)
        .apply {
            viewBinding = this
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = loginViewModel
        }
        .root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().findViewById<FloatingActionButton>(R.id.fab).apply {
            visibility = View.VISIBLE
            contentDescription = getString(R.string.fab_login_content_description)
            setImageResource(R.drawable.ic_login_24dp)
            setOnClickListener {
                loginViewModel.loginMe()
                loginViewModel.onWelcomeNavigated()
            }
        }
        observerUi()
    }

    private fun observerUi() {
        loginViewModel.navigateToWelcome.observe(viewLifecycleOwner) {
            it?.let {
                navController.navigate(toWelcomeFragment())
                loginViewModel.onWelcomeNavigated()
            }
        }
    }
}