package com.zenefit.zenefit_android.presentation.ui.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initBinding()
    }

    private fun initBinding() {
        binding.activity = this
    }

    fun onSocialAuthClicked() {
        Log.e("----", "onSocialAuthClicked: AUTH", )
    }
}