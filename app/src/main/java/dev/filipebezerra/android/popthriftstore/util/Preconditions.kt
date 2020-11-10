package dev.filipebezerra.android.popthriftstore.util

import org.jetbrains.annotations.Nullable

object Preconditions {

    fun checkState(expression: Boolean, @Nullable message: String) {
        if (!expression) {
            throw IllegalStateException(message)
        }
    }
}
