package com.zenefit.zenefit_android.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zenefit.zenefit_android.presentation.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    //private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        startActivity(Intent(this, LoginActivity::class.java)).also { finish() }
    }
}