package com.zenefit.zenefit_android.presentation.ui.find_policy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ActivityFindPolicyBinding
import com.zenefit.zenefit_android.presentation.ui.find_policy.find.FindPolicyFragment
import com.zenefit.zenefit_android.presentation.ui.find_policy.result.ResultFindPolicyFragment
import com.zenefit.zenefit_android.presentation.ui.find_policy.viewmodel.FindPolicyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindPolicyActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFindPolicyBinding
    private val viewModel : FindPolicyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_policy)

        initUi()
    }

    private fun initUi() {
        initFindPolicyFragment()
    }


    private fun initFindPolicyFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.find_policy_layout_container, FindPolicyFragment()).commit()
    }

    fun openResultFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.find_policy_layout_container, ResultFindPolicyFragment()).commit()
    }

}