package dev.filipebezerra.android.popthriftstore.ui.login

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.filipebezerra.android.popthriftstore.R
import dev.filipebezerra.android.popthriftstore.ui.welcome.WelcomeScreen
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @Test
    fun test_navigation_to_welcome_screen() {
        // Create a TestNavHostController and set the application nav graph
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            .apply {
                setGraph(R.navigation.navigation_graph)
            }

        // Create a graphical FragmentScenario for the WelcomeScreen
        val welcomeScenario = launchFragmentInContainer<WelcomeScreen>()

        // Set the NavController property on the fragment
        welcomeScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        onView(withId(R.id.login_fab)).perform(click())
        assertThat(navController.currentDestination?.id, `is`(equalTo(R.id.welcome_screen)))
    }
}