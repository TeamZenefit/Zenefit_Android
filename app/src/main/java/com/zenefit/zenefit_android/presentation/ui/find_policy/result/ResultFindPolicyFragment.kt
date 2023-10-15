package com.zenefit.zenefit_android.presentation.ui.find_policy.result

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zenefit.zenefit_android.presentation.ui.main.MainActivity
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.FragmentResultFindPolicyBinding
import com.zenefit.zenefit_android.presentation.ui.find_policy.viewmodel.FindPolicyViewModel
import com.zenefit.zenefit_android.presentation.ui.sign_up.SignUpActivity

class ResultFindPolicyFragment : Fragment() {
    private lateinit var binding : FragmentResultFindPolicyBinding
    private val viewModel : FindPolicyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_find_policy, container,false)

        initBinding()

        return binding.root
    }

    private fun initBinding() {
        binding.fragment = this
        binding.viewModel = viewModel
    }

    fun validateMove(count : Int) {
        when(count) {
            0 -> startActivity(Intent(requireActivity(), SignUpActivity::class.java)).also { requireActivity().finish() }
            else -> startActivity(Intent(requireActivity(), MainActivity::class.java)).also { requireActivity().finish() }
        }
    }

}