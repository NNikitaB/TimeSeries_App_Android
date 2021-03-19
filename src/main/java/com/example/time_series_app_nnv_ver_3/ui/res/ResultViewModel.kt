package com.example.time_series_app_nnv_ver_3.ui.res

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Result Fragment"

    }
    val text: LiveData<String> = _text
}