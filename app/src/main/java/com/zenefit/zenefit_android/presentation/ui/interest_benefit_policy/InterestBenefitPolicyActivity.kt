package com.zenefit.zenefit_android.presentation.ui.interest_benefit_policy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ActivityInterestBenefitPolicyBinding
import com.zenefit.zenefit_android.presentation.ui.dialog.DialogTwoButtons
import com.zenefit.zenefit_android.presentation.ui.interest_benefit_policy.adapter.InterestBenefitPolicyAdapter
import com.zenefit.zenefit_android.presentation.ui.interest_benefit_policy.viewmodel.InterestBenefitPolicyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InterestBenefitPolicyActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInterestBenefitPolicyBinding
    private val viewModel : InterestBenefitPolicyViewModel by viewModels()

    private val policyAdapter by lazy {
        InterestBenefitPolicyAdapter(intent.type ?: "", ::onRemoveClicked)
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
        validateType()
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
        observeDeleteResponseStatus()
    }

    private fun initPolicyRV() {
        binding.interestBenefitRvContent.apply{
            adapter = policyAdapter
            itemAnimator = null
        }
    }

    private fun observeDeleteStatus() {
        viewModel.writeStatus.observe(this) {
            policyAdapter.deleteStatus = it
            policyAdapter.notifyDataSetChanged()
        }
    }

    private fun validateType() {
        if(intent.type!!.contains("Interest")) requestInterestData()
        else requestBenefitData()
    }

    private fun requestInterestData() {
        lifecycleScope.launch {
            viewModel.requestInterestPolicyList().collect {
                policyAdapter.submitData(it)
            }
        }

        requestPolicyCountData()
    }

    private fun requestBenefitData() {
        lifecycleScope.launch {
            viewModel.requestBenefitPolicyList().collect() {
                policyAdapter.submitData(it)
            }
        }

        requestPolicyCountData()
    }

    private fun onRemoveClicked(type : String, policyId : Int) {
        DialogTwoButtons(policyId, if(type.contains("Interest")) ::removeInterestPolicy else ::removeBenefitPolicy).show(supportFragmentManager, if(intent.type!!.contains("Interest"))"REMOVE_INTEREST_POLICY" else "REMOVE_BENEFIT_POLICY")
    }

    private fun removeInterestPolicy(policyId: Int?) {
        viewModel.requestRemoveInterestPolicy(policyId!!)
    }

    private fun removeBenefitPolicy(policyId: Int?) {
        viewModel.requestRemoveBenefitPolicy(policyId!!)
    }

    private fun observeDeleteResponseStatus() {
        viewModel.deleteResponseStatus.observe(this) {
            policyAdapter.refresh()

            requestPolicyCountData()
        }
    }

    private fun requestPolicyCountData() {
        if(viewModel.type.value!!.contains("Interest")) viewModel.requestInterestPolicyCount()
        else viewModel.requestBenefitPolicyCount()
    }

    fun onBackPress() {
        finish()
    }


}