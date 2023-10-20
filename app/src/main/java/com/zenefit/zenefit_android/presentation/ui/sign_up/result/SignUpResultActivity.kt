package com.zenefit.zenefit_android.presentation.ui.sign_up.result

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ActivitySignUpResultBinding
import com.zenefit.zenefit_android.presentation.ui.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_result)

        binding.nickname = intent.type

        setTimer()
    }

    private fun setTimer() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            startActivity(Intent(this@SignUpResultActivity, MainActivity::class.java)).also { finish() }
        }
    }
}