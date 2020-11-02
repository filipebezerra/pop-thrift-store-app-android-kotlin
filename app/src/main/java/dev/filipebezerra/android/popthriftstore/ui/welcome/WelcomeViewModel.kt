package dev.filipebezerra.android.popthriftstore.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    private val _navigateToInstruction = MutableLiveData<Boolean>()
    val navigateToInstruction: LiveData<Boolean>
        get() = _navigateToInstruction

    fun startShopping() {
        _navigateToInstruction.value = true
    }

    fun onInstructionNavigated() {
        _navigateToInstruction.value = null
    }
}