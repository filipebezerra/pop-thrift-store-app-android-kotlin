package dev.filipebezerra.android.popthriftstore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.filipebezerra.android.popthriftstore.data.UserRepository
import dev.filipebezerra.android.popthriftstore.util.event.Event
import dev.filipebezerra.android.popthriftstore.util.ext.postEvent

class MainActivityViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _finishApplication = MutableLiveData<Event<Any>>()
    val finishApplication: LiveData<Event<Any>>
        get() = _finishApplication

    fun logout() {
        if (userRepository.isUserLoggedIn()) {
            userRepository.signOutUser()
            _finishApplication.postEvent(true)
        }
    }

    companion object {

        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                MainActivityViewModel(
                    ServiceLocator.provideUserRepository(),
                ) as T
        }
    }
}