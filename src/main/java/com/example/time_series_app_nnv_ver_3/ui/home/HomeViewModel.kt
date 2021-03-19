package com.example.time_series_app_nnv_ver_3.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.charts.LineChart
import kotlin.coroutines.coroutineContext

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    //private var _chart:LineChart=LineChart(coroutineContext)
    var text: LiveData<String> = _text

}