package com.zenefit.zenefit_android.presentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ActivityMainBinding
import com.zenefit.zenefit_android.presentation.ui.main.viewmodel.MainViewModel
import com.zenefit.zenefit_android.presentation.ui.manual.ManualActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initBinding()
        initUi()

    }

    private fun initBinding() {
        binding.activity = this
    }

    private fun initUi() {
        initBottomNav()
    }

    private fun initBottomNav() {
        binding.mainLayoutBnv.apply {
            itemIconTintList = null
        }
    }

    fun openManualActivity() {
        startActivity(Intent(this, ManualActivity::class.java))
    }
}