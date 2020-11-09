package dev.filipebezerra.android.popthriftstore.data

class UserRepository {

    companion object {
        @Volatile
        private var CURRENT_USER: User? = null
    }

    fun getCurrentUser(): User? = CURRENT_USER

    @Synchronized
    fun signInUser(fullName: String, email: String): User {
        CURRENT_USER = User(
            email = email,
            fullName = fullName
        )
        return CURRENT_USER!!
    }

    @Synchronized
    fun signOutUser() {
        CURRENT_USER = null
    }
}