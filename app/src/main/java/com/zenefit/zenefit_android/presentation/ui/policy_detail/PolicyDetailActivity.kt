package com.zenefit.zenefit_android.presentation.ui.policy_detail

import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.toSpannable
import androidx.databinding.DataBindingUtil
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ActivityPolicyDetailBinding
import com.zenefit.zenefit_android.presentation.ui.policy_detail.viewmodel.PolicyDetailViewModel

class PolicyDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPolicyDetailBinding
    private val viewModel : PolicyDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_policy_detail)

        initType()
        initBinding()
        initUi()
    }

    private fun initType() {
        viewModel.setType(intent.type ?: "")
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.policyData = viewModel.policyDetailData()
    }

    private fun initUi() {
        initSpannable()
    }

    private fun initSpannable() {
        if(viewModel.type.value == "CAN") setSpannable() else binding.policyDetailTvTitle.text = resources.getString(R.string.policy_detail_benefit_title_when_cant_apply).format(viewModel.policyDetailData().title)
    }

    private fun setSpannable() {
        val text = viewModel.type.value!!.returnTitle(viewModel.policyDetailData().title, viewModel.policyDetailData().benefit).reversed()
        val startIndex = text.length - text.indexOf("월") - 1; val endIndex = text.length - text.indexOf("원")
        val span = text.reversed().toSpannable()
        span.setSpan(ForegroundColorSpan(resources.getColor(R.color.primary_normal)), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.policyDetailTvTitle.text = span
    }


    /** Dummy **/
    fun String.returnTitle(title : String, benefit : Int?) : String {
        return if(contains("CANT")) resources.getString(R.string.policy_detail_benefit_title_when_cant_apply).format(title)
        else resources.getString(R.string.policy_detail_benefit_title_when_can_apply).format(title, benefit)
    }
}