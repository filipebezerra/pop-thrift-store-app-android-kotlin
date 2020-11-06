package dev.filipebezerra.android.popthriftstore.util.ext

import androidx.lifecycle.MutableLiveData
import dev.filipebezerra.android.popthriftstore.util.event.Event

fun <T> MutableLiveData<Event<T>>.postEvent(content: T) {
    postValue(Event(content))
}