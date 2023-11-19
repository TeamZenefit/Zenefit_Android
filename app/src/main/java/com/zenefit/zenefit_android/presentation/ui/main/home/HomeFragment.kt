package com.zenefit.zenefit_android.presentation.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.data.remote.response.ResponseHomeData
import com.zenefit.zenefit_android.data.remote.response.ResponsePolicyCountData
import com.zenefit.zenefit_android.databinding.FragmentHomeBinding
import com.zenefit.zenefit_android.presentation.ui.interest_benefit_policy.InterestBenefitPolicyActivity
import com.zenefit.zenefit_android.presentation.ui.main.home.adapter.HomePolicyDeadlineAdapter
import com.zenefit.zenefit_android.presentation.ui.main.home.adapter.HomePolicyRecommendAdapter
import com.zenefit.zenefit_android.presentation.ui.main.home.viewmodel.HomeViewModel
import com.zenefit.zenefit_android.presentation.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val viewModel : HomeViewModel by viewModels()

    private val recommendPolicyAdapter by lazy {
        HomePolicyRecommendAdapter(::onRecommendApplyClicked)
    }

    private val deadlinePolicyAdapter by lazy {
        HomePolicyDeadlineAdapter(::onDeadlineApplyClicked)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container ,false)

        initBinding()
        initUi()
        initObserve()

        return binding.root
    }

    private fun initBinding() {
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initUi() {
        initPolicyRecommend()
        initPolicyDeadline()
    }

    private fun initObserve() {
        observeHomeData()
    }

    private fun initPolicyRecommend() {
        binding.fgHomeRvRecommendPolicy.adapter = recommendPolicyAdapter
    }

    private fun initPolicyDeadline() {
        binding.fgHomeRvDeadlinePolicy.adapter = deadlinePolicyAdapter
    }

    private fun onRecommendApplyClicked(policyId : Int) {
        Log.e("----", "onRecommendApplyClicked: $policyId", )
    }

    private fun onDeadlineApplyClicked(policyId : Int) {
        Log.e("----", "onDeadlineApplyClicked: $policyId", )
    }

    private fun observeHomeData() {
        viewModel.homeData.observe(viewLifecycleOwner) {
            it.recommendPolicy?.setRecommendData()
            it.endDatePolicy?.setDeadlineData()
        }
    }

    private fun List<ResponseHomeData.ResponseHomePolicyListData>.setRecommendData() {
        recommendPolicyAdapter.apply{
            policyList = this@setRecommendData as MutableList
            notifyItemRangeChanged(0, this@setRecommendData.size)
        }
    }

    private fun List<ResponseHomeData.ResponseHomePolicyListData>.setDeadlineData() {
        deadlinePolicyAdapter.apply{
            policyList = this@setDeadlineData as MutableList
            notifyItemRangeChanged(0, this@setDeadlineData.size)
        }
    }

    fun onMyPolicyClicked(type : String) {
        startActivity(Intent(requireActivity(), InterestBenefitPolicyActivity::class.java).setType(type))
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestHomeData()
    }

}