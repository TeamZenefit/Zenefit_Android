package com.zenefit.zenefit_android.presentation.ui.interest_benefit_policy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ActivityInterestBenefitPolicyBinding
import com.zenefit.zenefit_android.presentation.ui.interest_benefit_policy.adapter.InterestBenefitPolicyAdapter
import com.zenefit.zenefit_android.presentation.ui.interest_benefit_policy.viewmodel.InterestBenefitPolicyViewModel

class InterestBenefitPolicyActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInterestBenefitPolicyBinding
    private val viewModel : InterestBenefitPolicyViewModel by viewModels()

    private val policyAdapter by lazy {
        InterestBenefitPolicyAdapter(intent.type ?: "")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_interest_benefit_policy)

        initType()
        initUi()
        initBinding()
        initObserve()
    }

    private fun initType() {
        viewModel.setActivityType(intent.type ?: "")
    }

    private fun initUi() {
        initPolicyRV()
    }

    private fun initBinding() {
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initObserve() {
        observeDeleteStatus()
    }

    private fun initPolicyRV() {
        binding.interestBenefitRvContent.adapter = policyAdapter
    }

    private fun observeDeleteStatus() {
        viewModel.writeStatus.observe(this) {
            policyAdapter.deleteStatus = it
            policyAdapter.notifyDataSetChanged()
        }
    }

    fun onBackPress() {
        finish()
    }


}