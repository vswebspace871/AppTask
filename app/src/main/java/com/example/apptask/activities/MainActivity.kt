package com.example.apptask.activities

import android.os.Build
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.apptask.R

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        handleBackButton()

    }

    private fun handleBackButton() {
        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                handleBackButtonPressed()
            }
        } else {
            onBackPressedDispatcher.addCallback(
                this,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        // Back is pressed... Finishing the activity
                        handleBackButtonPressed()
                    }
                })
        }
    }

    private fun handleBackButtonPressed() {
       val currentFragment = findNavController(R.id.fragmentContainerView).currentDestination?.id

       currentFragment?.let {currentFragmentID ->
           if (currentFragmentID == R.id.splashGetStarted) {
               finish()
           }else {
               navController.popBackStack()
           }
       }

   }
}