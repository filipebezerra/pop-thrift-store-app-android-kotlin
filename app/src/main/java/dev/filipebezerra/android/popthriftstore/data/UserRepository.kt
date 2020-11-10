package dev.filipebezerra.android.popthriftstore.data

import dev.filipebezerra.android.popthriftstore.util.Preconditions

class UserRepository {

    companion object {
        @Volatile
        private var CURRENT_USER: User? = null
    }

    fun getCurrentUser(): User? = CURRENT_USER

    @Synchronized
    fun signInUser(fullName: String, email: String): User {
        Preconditions.checkState(CURRENT_USER == null, "User already logged in")

        val currentUser = User(
            email = email,
            fullName = fullName
        )
        CURRENT_USER = currentUser
        return currentUser
    }

    @Synchronized
    fun signOutUser() {
        Preconditions.checkState(CURRENT_USER != null, "No user logged in")

        CURRENT_USER = null
    }
}