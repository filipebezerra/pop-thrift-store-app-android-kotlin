package dev.filipebezerra.android.popthriftstore

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import dev.filipebezerra.android.popthriftstore.MainActivityViewModel.Companion.provideFactory
import dev.filipebezerra.android.popthriftstore.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels { provideFactory() }

    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var viewBinding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<MainActivityBinding>(
            this,
            R.layout.main_activity
        ).apply {
            viewBinding = this
            setSupportActionBar(this.toolbar)
            appBarConfiguration = AppBarConfiguration(navController.graph)
            setupActionBarWithNavController(
                navController,
                appBarConfiguration
            )
        }
        setupNavController()
        setupActionBarNavigation()
        observeUi()
    }

    private fun setupNavController() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.login_screen,
                R.id.welcome_screen,
                R.id.instruction_screen -> {
                    viewBinding.toolbar.navigationIcon = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_baseline_close_24
                    )
                    viewBinding.toolbar.navigationContentDescription =
                        getString(R.string.navigation_close_icon_content_description)
                }
            }
        }
    }

    private fun setupActionBarNavigation() {
        viewBinding.toolbar.setNavigationOnClickListener {
            viewModel.onActionBarNavigation(navController.currentDestination)
        }
    }

    private fun observeUi() {
        viewModel.finishApplication.observe(this) {
            finish()
        }
        viewModel.navigateUp.observe(this) {
            onSupportNavigateUp()
        }
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}