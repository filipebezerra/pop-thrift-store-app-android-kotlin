package dev.filipebezerra.android.popthriftstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import dev.filipebezerra.android.popthriftstore.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<MainActivityBinding>(
            this,
            R.layout.main_activity
        ).apply {
            setSupportActionBar(this.toolbar)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.title = destination.label
        }
    }
}