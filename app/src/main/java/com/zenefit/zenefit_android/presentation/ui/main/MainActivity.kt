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
import com.zenefit.zenefit_android.presentation.ui.main.policy_list.PolicyListFragment
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initUi() {
        initBottomNav()
    }

    private fun initBottomNav() {
        binding.mainLayoutBnv.apply {
            itemIconTintList = null
        }

        binding.mainLayoutBnv.setOnItemSelectedListener {
            returnTargetFragment(it.itemId).also { viewModel.setCurrentFragmentName(it.javaClass.toString()) }.setFragment()
            return@setOnItemSelectedListener true
        }

        binding.mainLayoutBnv.selectedItemId = R.id.bottom_nav_home

        binding.mainLayoutBnv.setOnItemReselectedListener { return@setOnItemReselectedListener }
    }

    private fun returnTargetFragment(itemId : Int) : Fragment {
        return when(itemId) {
            R.id.bottom_nav_home -> HomeFragment()
            R.id.bottom_nav_welfare -> PolicyFragment().also { binding.mainTvTitle.text = "정책" }
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

    /** 수정 필요 **/

    fun setDetailFragments(type : String) {
        if(type.contains("현금 정책")) binding.mainTvTitle.text = "현금 정책"
        else if(type.contains("대출 정책")) binding.mainTvTitle.text = "대출 정책"
        else if(type.contains("사회 서비스")) binding.mainTvTitle.text = "사회 서비스"

        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_layout_container, PolicyListFragment(type)).commit()
        viewModel.setCurrentFragmentName("PolicyList")
    }

    fun removeDetailFragment() {
        binding.mainTvTitle.text = "정책"
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction().commit()
    }
}