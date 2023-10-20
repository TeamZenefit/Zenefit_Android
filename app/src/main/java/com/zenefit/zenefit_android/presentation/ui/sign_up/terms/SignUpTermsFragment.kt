package com.zenefit.zenefit_android.presentation.ui.sign_up.terms

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zenefit.zenefit_android.presentation.ui.main.MainActivity
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.FragmentSignUpTermsBinding
import com.zenefit.zenefit_android.presentation.ui.sign_up.result.SignUpResultActivity
import com.zenefit.zenefit_android.presentation.ui.sign_up.terms.adapter.SignUpTermsAdapter
import com.zenefit.zenefit_android.presentation.ui.sign_up.viewmodel.SignUpViewModel

class SignUpTermsFragment : Fragment() {
    private lateinit var binding : FragmentSignUpTermsBinding
    private val viewModel : SignUpViewModel by activityViewModels()

    private val termsAdapter : SignUpTermsAdapter by lazy {
        SignUpTermsAdapter(::onTermsClicked)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_terms, container, false)

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
        binding.fgSignUpTermsRvTerms.adapter = termsAdapter
    }

    private fun initObserve() {
        observeTerms()
        observeSignUpResult()
    }

    fun onAllTermsClicked() {
        binding.fgSignUpTermsCheckAll.isChecked.apply {
            viewModel.onAllTermsClicked(this)
            termsAdapter.setAllClicked(this)
        }
    }

    fun onFinishSignUpClicked() {
        viewModel.requestSignUp()
    }

    private fun onTermsClicked() {
        viewModel.onTermsClicked()
    }

    private fun observeTerms() {
        viewModel.termsStateList.observe(viewLifecycleOwner) {
            termsAdapter.terms = it
        }
    }

    private fun observeSignUpResult() {
        viewModel.signUpResultMessage.observe(viewLifecycleOwner) {
            if(it.contains("SUCCESS")) startActivity(Intent(requireActivity(), SignUpResultActivity::class.java).setType(it.replace("SUCCESS", ""))).apply { requireActivity().finish() }
            else Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

}