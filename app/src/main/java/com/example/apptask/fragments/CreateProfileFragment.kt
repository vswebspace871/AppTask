package com.example.apptask.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.apptask.databinding.FragmentCreateProfileBinding

class CreateProfileFragment : Fragment() {

    private lateinit var binding : FragmentCreateProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include.tvTitle.text = "Create Profile"

        binding.include.tvHeaderTitle.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}