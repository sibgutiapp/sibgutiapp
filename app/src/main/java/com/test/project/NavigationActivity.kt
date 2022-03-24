package com.test.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.viewbinding.library.activity.viewBinding
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.test.project.databinding.ActivityNavigationBinding


class NavigationActivity : AppCompatActivity(){

    private val binding: ActivityNavigationBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_navigation)
    }

}



