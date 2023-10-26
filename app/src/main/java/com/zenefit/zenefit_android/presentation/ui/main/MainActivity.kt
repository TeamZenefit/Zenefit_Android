package com.zenefit.zenefit_android.presentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ActivityMainBinding
import com.zenefit.zenefit_android.presentation.ui.main.calendar.CalendarFragment
import com.zenefit.zenefit_android.presentation.ui.main.home.HomeFragment
import com.zenefit.zenefit_android.presentation.ui.main.policy.PolicyFragment
import com.zenefit.zenefit_android.presentation.ui.main.setting.SettingFragment
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

        binding.mainLayoutBnv.setOnItemSelectedListener {
            returnTargetFragment(it.itemId).setFragment()
            return@setOnItemSelectedListener true
        }
    }

    private fun returnTargetFragment(itemId : Int) : Fragment {
        return when(itemId) {
            R.id.bottom_nav_home -> HomeFragment()
            R.id.bottom_nav_welfare -> PolicyFragment()
            R.id.bottom_nav_calendar -> CalendarFragment()
            R.id.bottom_nav_my -> SettingFragment()
            else -> HomeFragment()
        }
    }

    private fun Fragment.setFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.main_layout_container, this).commit()
    }

    fun openManualActivity() {
        startActivity(Intent(this, ManualActivity::class.java))
    }
}