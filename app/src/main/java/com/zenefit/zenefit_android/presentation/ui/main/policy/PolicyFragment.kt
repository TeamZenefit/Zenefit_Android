package com.zenefit.zenefit_android.presentation.ui.main.policy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.zenefit.zenefit_android.databinding.FragmentPolicyBinding

class PolicyFragment : Fragment() {
    private lateinit var binding : FragmentPolicyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPolicyBinding.inflate(layoutInflater)
        return binding.root
    }
}