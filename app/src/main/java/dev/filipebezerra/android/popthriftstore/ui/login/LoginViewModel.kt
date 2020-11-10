package dev.filipebezerra.android.popthriftstore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.ServiceLocator
import dev.filipebezerra.android.popthriftstore.data.UserRepository
import dev.filipebezerra.android.popthriftstore.util.event.Event
import dev.filipebezerra.android.popthriftstore.util.ext.postEvent

class LoginViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    val email = MutableLiveData<String>()
    private val _emailValidationError = MutableLiveData<Int?>()
    val emailValidationError: LiveData<Int?>
        get() = _emailValidationError

    val password = MutableLiveData<String>()
    private val _passwordValidationError = MutableLiveData<Int?>()
    val passwordValidationError: LiveData<Int?>
        get() = _passwordValidationError

    private val _messaging = MutableLiveData<Event<Int>>()
    val messaging: LiveData<Event<Int>>
        get() = _messaging

    private val _navigateToWelcome = MutableLiveData<Event<Any>>()
    val navigateToWelcome: LiveData<Event<Any>>
        get() = _navigateToWelcome

    fun loginMe() = makeLogin()

    fun createAccount() = makeLogin()

    private fun makeLogin() {
        if (validateForm()) {
            userRepository.signInUser(email.value!!, email.value!!)
            _navigateToWelcome.postEvent(true)
        } else {
            _messaging.postEvent(R.string.please_complete_login_form)
        }
    }

    private fun validateForm() = listOf(
        validateEmail(),
        validatePassword(),
    ).all { it }

    private fun validateEmail() = when {
        email.value.isNullOrEmpty() -> {
            _emailValidationError.value = R.string.email_is_required
            false
        }
        else -> {
            _emailValidationError.value = null
            true
        }
    }

    private fun validatePassword() = when {
        password.value.isNullOrEmpty() -> {
            _passwordValidationError.value = R.string.password_is_required
            false
        }
        else -> {
            _passwordValidationError.value = null
            true
        }
    }

    fun onChangeEmail() {
        if (_emailValidationError.value != null && !email.value.isNullOrEmpty()) {
            _emailValidationError.value = null
        }
    }

    fun onChangePassword() {
        if (_passwordValidationError.value != null && !password.value.isNullOrEmpty()) {
            _passwordValidationError.value = null
        }
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                LoginViewModel(
                    ServiceLocator.provideUserRepository(),
                ) as T
        }
    }
}