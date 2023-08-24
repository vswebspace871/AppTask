package com.example.apptask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val number = MutableLiveData<String>()
    val otp = MutableLiveData<String>()
    val error = MutableLiveData<String?>()

    fun generateOTP(){
        val err = checkValidNumber(number.value)
        if (err != null) {
           error.postValue(err)
        }else {
            number.value?.let {
                otp.value = getOTP(it)
            }
        }
    }

    private fun getOTP(value: String) : String {
        val phnArr = value.toCharArray()
        val otp: StringBuilder = StringBuilder()
        otp.append(phnArr[0], phnArr[1], phnArr[phnArr.size - 2], phnArr[phnArr.size - 1])
        return otp.toString()
    }

    private fun checkValidNumber(value: String?):String? {
        if (value.isNullOrEmpty()){
            return "Fill Phone Number First"
        }else if (value.length != 10){
            return "Enter A Valid 10 Digit Phone Number"
        }
        return null
    }


}