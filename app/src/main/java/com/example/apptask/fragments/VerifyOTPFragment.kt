package com.example.apptask.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apptask.R
import com.example.apptask.databinding.FragmentVerifyOTPBinding

class VerifyOTPFragment : Fragment() {

    private lateinit var binding : FragmentVerifyOTPBinding
    private var userEnteredOTP = ""

    val args : VerifyOTPFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVerifyOTPBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numberPhn = args.number
        val OTP = args.otp

        binding.num.text = "+91 $numberPhn"

        binding.include.tvHeaderTitle.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.firstPinView.addTextChangedListener {
            userEnteredOTP = it.toString()
        }
        binding.button.setOnClickListener {
            if (userEnteredOTP.length == 4 && userEnteredOTP.isNotEmpty()){
                if (userEnteredOTP == OTP.toString()){
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_verifyOTPFragment_to_createProfileFragment)
                }
            }
            else Toast.makeText(requireContext(), "Incorrect OTP", Toast.LENGTH_LONG).show()
        }
    }
}