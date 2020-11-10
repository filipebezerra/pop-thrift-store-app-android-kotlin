package dev.filipebezerra.android.popthriftstore.data

import dev.filipebezerra.android.popthriftstore.ServiceLocator
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {

    private val userRepository: UserRepository = ServiceLocator.provideUserRepository()

    private lateinit var user: User

    @Before
    fun setUp() {
        user = User(
            email = "filipebzerra@gmail.com",
            fullName = "Filipe Bezerra"
        )
    }

    @After
    fun tearDown() {}

    @Test
    fun given_no_user_when_get_current_user_then_return_null() {
        userRepository.getCurrentUser().also { currentUser ->
            assertNull(currentUser)
        }
    }

    @Test
    fun given_user_when_sign_in_then_return_current_user() {
        userRepository.apply {
            signInUser("Filipe Bezerra", "filipebzerra@gmail.com")
            getCurrentUser().also { currentUser ->
                assertNotNull(currentUser)
                assertEquals("filipebzerra@gmail.com", currentUser!!.email)
                assertEquals("Filipe Bezerra", currentUser.fullName)
            }
            signOutUser()
        }
    }

    @Test
    fun given_logged_in_user_when_logging_out_then_no_current_user_should_be_returned() {
        userRepository.apply {
            signInUser("Filipe Bezerra", "filipebzerra@gmail.com")
            assertNotNull(getCurrentUser())
            signOutUser()
            assertNull(getCurrentUser())
        }
    }
}