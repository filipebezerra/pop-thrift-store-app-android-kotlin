package dev.filipebezerra.android.popthriftstore.ui.addtowishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.util.event.Event
import dev.filipebezerra.android.popthriftstore.util.ext.postEvent

class AddToWishListViewModel : ViewModel() {

    private val _messaging = MutableLiveData<Event<Int>>()
    val messaging: LiveData<Event<Int>>
        get() = _messaging

    val wishListName = MutableLiveData<String>()

    fun addToWishList() {
        _messaging.postEvent(R.string.added_to_wish_list)
    }
}