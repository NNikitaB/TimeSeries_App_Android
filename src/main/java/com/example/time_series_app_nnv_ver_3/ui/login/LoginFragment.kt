package com.example.time_series_app_nnv_ver_3.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.time_series_app_nnv_ver_3.R

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        loginViewModel =
                ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        return root
    }
}