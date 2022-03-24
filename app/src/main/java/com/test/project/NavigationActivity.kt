package com.test.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.viewbinding.library.activity.viewBinding
import androidx.navigation.fragment.NavHostFragment
import com.test.project.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity(R.layout.activity_navigation){

    private val binding: ActivityNavigationBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }

}



