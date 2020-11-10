package dev.filipebezerra.android.popthriftstore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.databinding.LoginFragmentBinding
import dev.filipebezerra.android.popthriftstore.ui.login.LoginViewModel.Companion.provideFactory
import dev.filipebezerra.android.popthriftstore.ui.util.setupSnackbar
import dev.filipebezerra.android.popthriftstore.util.event.EventObserver
import dev.filipebezerra.android.popthriftstore.ui.login.LoginScreenDirections.Companion.actionLoginScreenToWelcomeScreen as toWelcomeFragment

class LoginScreen : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels { provideFactory() }

    private val navController by lazy { findNavController() }

    private lateinit var viewBinding: LoginFragmentBinding

    private val fab by lazy { requireActivity().findViewById<FloatingActionButton>(R.id.fab) }

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
        fab.apply {
            visibility = View.VISIBLE
            contentDescription = getString(R.string.fab_login_content_description)
            setImageResource(R.drawable.ic_login_24dp)
            setOnClickListener { loginViewModel.loginMe() }
        }
        observerUi()
        setupSnackbar()
    }

    private fun observerUi() {
        loginViewModel.navigateToWelcome.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(toWelcomeFragment())
        })
        loginViewModel.emailValidationError.observe(viewLifecycleOwner) { errorRes ->
            viewBinding.emailInputLayout.error = errorRes?.let { getString(errorRes) }
        }
        loginViewModel.passwordValidationError.observe(viewLifecycleOwner) { errorRes ->
            viewBinding.passwordInputLayout.error = errorRes?.let { getString(errorRes) }
        }
    }

    private fun setupSnackbar() = view?.setupSnackbar(
        viewLifecycleOwner,
        loginViewModel.messaging,
        LENGTH_LONG,
        fab,
    )
}