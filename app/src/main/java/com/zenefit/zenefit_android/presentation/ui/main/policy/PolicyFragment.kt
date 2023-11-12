package com.zenefit.zenefit_android.presentation.ui.main.policy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.data.data.DummyPolicy
import com.zenefit.zenefit_android.databinding.FragmentPolicyBinding
import com.zenefit.zenefit_android.presentation.ui.main.MainActivity
import com.zenefit.zenefit_android.presentation.ui.main.policy.adapter.PolicyAdapter
import com.zenefit.zenefit_android.presentation.util.CustomSnackBar

class PolicyFragment : Fragment() {
    private lateinit var binding : FragmentPolicyBinding
    private val policyAdapter by lazy {
        PolicyAdapter(::onCalendarClicked, ::onListOpenClicked, ::onDetailOpenClicked, policyDummy())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_policy, container, false)

        initUi()

        return binding.root
    }

    private fun initUi() {
        binding.fgPolicyRvContent.adapter = policyAdapter
    }

    private fun onListOpenClicked(type : String) {
        (requireActivity() as MainActivity).setDetailFragments(type)
    }

    private fun onDetailOpenClicked(policyId : Int) {

    }

    private fun onCalendarClicked() {
        CustomSnackBar.makeSnackBar(binding.root, "ADD_CALENDAR").show()
    }

    /** Dummy **/
    private fun policyDummy() : List<DummyPolicy> =
        listOf(DummyPolicy(
            "현금 정책",
            0,
            "현금 정책 이름",
            "",
            "서울시",
            "강남구",
            "현금 정책 설명",
            "",
            2,
            "300"
        ),
            DummyPolicy(
                "대출 정책",
                0,
                "대출 정책 이름",
                "",
                "서울시",
                "강남구",
                "대출 정책 설명",
                "",
                2,
                "400"
            ),
            DummyPolicy(
                "사회 서비스",
                0,
                "사회 서비스 이름",
                "",
                "서울시",
                "강남구",
                "사회 서비스 설명",
                "",
                2,
                "500"
            ))
}