package dev.filipebezerra.android.popthriftstore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    private val _navigateToWelcome = MutableLiveData<Boolean>()
    val navigateToWelcome: LiveData<Boolean>
        get() = _navigateToWelcome

    fun loginMe() {
        _navigateToWelcome.value = true
    }

    fun createAccount() {
        _navigateToWelcome.value = true
    }

    fun onWelcomeNavigated() {
        _navigateToWelcome.value = null
    }
}