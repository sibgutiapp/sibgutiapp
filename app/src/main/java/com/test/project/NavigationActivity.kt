package com.test.project

import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.test.project.databinding.ActivityNavigationBinding


class NavigationActivity : AppCompatActivity() {

    private val binding: ActivityNavigationBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        initBottomMenu()
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNavigationMenu()
                R.id.profileFragment -> showBottomNavigationMenu()
                else -> hideBottomNavigationMenu()
            }
        }
        val appBarConfiguration =
            AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigationMenu.setupWithNavController(navController)
    }

    private fun initBottomMenu() {
        binding.bottomNavigationMenu.itemIconTintList = null
    }

    private fun showBottomNavigationMenu() {
        binding.bottomNavigationMenu.visibility = View.VISIBLE
    }

    private fun hideBottomNavigationMenu() {
        binding.bottomNavigationMenu.visibility = View.GONE
    }
}




