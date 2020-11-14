package dev.filipebezerra.android.popthriftstore.ui.addtowishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.util.event.Event
import dev.filipebezerra.android.popthriftstore.util.ext.postEvent

class AddToWishListViewModel : ViewModel() {

    private val _productAdded = MutableLiveData<Event<Int>>()
    val productAdded: LiveData<Event<Int>>
        get() = _productAdded

    val wishListName = MutableLiveData<String>()

    fun addToWishList() {
        _productAdded.postEvent(R.string.added_to_wish_list)
    }
}