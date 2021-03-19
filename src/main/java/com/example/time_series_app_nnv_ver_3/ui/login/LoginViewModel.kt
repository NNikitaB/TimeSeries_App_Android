package com.example.time_series_app_nnv_ver_3.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is LogIn Fragment"

    }
    val text: LiveData<String> = _text
}