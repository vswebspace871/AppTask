package com.example.apptask.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.apptask.R

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateFromSplash()

    }

    private fun navigateFromSplash() {
        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                findNavController().navigate(R.id.action_splashFragment_to_splashGetStarted)
            }
        }, 2000)
    }
}