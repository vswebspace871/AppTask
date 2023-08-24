package com.example.apptask.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.apptask.databinding.FragmentEnterMobileInfoBinding
import com.example.apptask.databinding.OtpDialogBinding
import com.example.apptask.showToast
import com.example.apptask.viewmodel.MainViewModel


class EnterMobileInfoFragment : Fragment() {

    private lateinit var binding: FragmentEnterMobileInfoBinding
    private val viewmodel : MainViewModel by viewModels()
    private var OTP : Int = 0
    private lateinit var PhnNumber : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEnterMobileInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewmodel()

        binding.include.tvHeaderTitle.setOnClickListener {
            findNavController().popBackStack()
        }

        viewmodel.error.observe(requireActivity(), Observer {
            if (it != null){
                context?.showToast(it.toString())
            }
        })

        viewmodel.number.observe(requireActivity(), Observer {
            PhnNumber = it.toString()
        })

        viewmodel.otp.observe(requireActivity(), Observer {
            it?.let {
                OTP = it.toInt()
                showOtpDialog(PhnNumber, OTP)
            }
        })
    }

    private fun initializeViewmodel() {
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = requireActivity()
        binding.executePendingBindings()
    }

    private fun showOtpDialog(phnNumber: String, otp: Int) {
        val _binding = OtpDialogBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(requireContext()).setView(_binding.root)
            .setCancelable(false)
            .show()
        val window: Window? = dialog.window
        window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        _binding.tvOtp.text = "$otp"

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                dialog.dismiss()
                val action =
                    EnterMobileInfoFragmentDirections.actionEnterMobileInfoFragmentToVerifyOTPFragment(
                        phnNumber,
                        otp
                    )
                findNavController().navigate(action)

            }
        }, 3000)
    }
}