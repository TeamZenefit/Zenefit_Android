package com.zenefit.zenefit_android.presentation.ui.find_policy.find

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.FragmentFindPolicyBinding
import com.zenefit.zenefit_android.presentation.ui.find_policy.FindPolicyActivity
import com.zenefit.zenefit_android.presentation.ui.find_policy.viewmodel.FindPolicyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FindPolicyFragment : Fragment() {
    private lateinit var binding: FragmentFindPolicyBinding
    private val viewModel: FindPolicyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_policy, container, false)

        initUi()
        initObserve()

        return binding.root
    }

    private fun initUi() {
        initTextWhenAfter3Sec()
    }

    private fun initObserve() {
        viewModel.findPolicyResult.observe(viewLifecycleOwner) {
            (requireActivity() as FindPolicyActivity).openResultFragment()
        }
    }

    private fun initTextWhenAfter3Sec() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            changeTextWhenAfter3Sec()
        }
    }

    private fun changeTextWhenAfter3Sec() {
        binding.fgFindPolicyTvTitle.text = resources.getString(R.string.find_policy_title_after_3sec)
        binding.fgFindPolicyTvSubTitle.text = resources.getString(R.string.find_policy_sub_title_after_3sec)
    }
}