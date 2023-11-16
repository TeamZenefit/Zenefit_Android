package com.zenefit.zenefit_android.presentation.ui.main.policy_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.snackbar.Snackbar

import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.data.data.DummyPolicy
import com.zenefit.zenefit_android.databinding.FragmentPolicyListBinding
import com.zenefit.zenefit_android.presentation.ui.main.policy_list.adapter.PolicyListAdapter
import com.zenefit.zenefit_android.presentation.ui.main.policy_list.viewmodel.PolicyListViewModel
import com.zenefit.zenefit_android.presentation.ui.policy_detail.PolicyDetailActivity
import com.zenefit.zenefit_android.presentation.util.CustomSnackBar

class PolicyListFragment(private val type : String) : Fragment() {
    private lateinit var binding : FragmentPolicyListBinding
    private val viewModel : PolicyListViewModel by viewModels()
    private val policyListAdapter by lazy {
        PolicyListAdapter(::onOpenDetail, ::showSnackBarWithCalendar, policyDummy())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_policy_list, container, false)

        initBinding()
        initUi()

        return binding.root
    }

    private fun initBinding() {
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initUi() {
        initChipGroup()
        initContentRV()
    }

    private fun initContentRV() {
        binding.fgPolicyListRvContent.adapter = policyListAdapter
    }

    private fun initChipGroup() {
        binding.fgPolicyListChipgroupFilter.apply {
            isSingleSelection = true
            for(i in returnChipTitles()) {
                addView(i.makeChip())
            }

            this[0].id.apply {
                check(this)
            }
        }
    }

    private fun String.makeChip() : Chip {
        return Chip(requireContext()).apply {
            text = this@makeChip
            setChipStyles()
        }
    }

    private fun Chip.setChipStyles() {
        this.apply {
            setTextAppearance(R.style.policy_list_chip_text)
            setChipBackgroundColorResource(R.color.selector_policy_list_chip)
            chipStrokeWidth = 0F
            isCheckable = true
            isCheckedIconVisible = false
            shapeAppearanceModel = ShapeAppearanceModel().withCornerSize(50F)
        }
    }

    private fun showSnackBarWithCalendar() {
        CustomSnackBar.makeSnackBar(binding.root, "ADD_CALENDAR").show()
        //.also { it.animationMode = Snackbar.ANIMATION_MODE_SLIDE}
    }

    private fun onOpenDetail() {
        startActivity(Intent(requireActivity(), PolicyDetailActivity::class.java).setType("CANT"))
    }

    /** Dummy **/
    private fun returnChipTitles() : List<String> = listOf("전체", "취업", "창업", "주거, 금융", "생활", "정책1", "정책2", "정책3")

    private fun policyDummy() : List<DummyPolicy> =
        listOf(
            DummyPolicy("정책", 0, "정책 이름", "", "서울시", "강남구", "정책 설명", "", 2, "300"),
            DummyPolicy("정책", 0, "정책 이름", "", "서울시", "강남구", "정책 설명", "", 2, "300"),
            DummyPolicy("정책", 0, "정책 이름", "", "서울시", "강남구", "정책 설명", "", 2, "300"),
            DummyPolicy("정책", 0, "정책 이름", "", "서울시", "강남구", "정책 설명", "", 2, "300"),
            DummyPolicy("정책", 0, "정책 이름", "", "서울시", "강남구", "정책 설명", "", 2, "300"),
            DummyPolicy("정책", 0, "정책 이름", "", "서울시", "강남구", "정책 설명", "", 2, "300"),
            DummyPolicy("정책", 0, "정책 이름", "", "서울시", "강남구", "정책 설명", "", 2, "300"),
            DummyPolicy("정책", 0, "정책 이름", "", "서울시", "강남구", "정책 설명", "", 2, "300"),
            DummyPolicy("정책", 0, "정책 이름", "", "서울시", "강남구", "정책 설명", "", 2, "300")
        )
}