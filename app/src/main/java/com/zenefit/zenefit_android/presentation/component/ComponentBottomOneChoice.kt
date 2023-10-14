package com.zenefit.zenefit_android.presentation.component

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ComponentBottomOneChoiceBinding
import com.zenefit.zenefit_android.presentation.component.adapter.ComponentBottomOneChoiceAdapter
import com.zenefit.zenefit_android.presentation.ui.sign_up.viewmodel.SignUpViewModel

class ComponentBottomOneChoice(private val contents : List<String>, private val selected : String?) : BottomSheetDialogFragment() {
    private lateinit var binding : ComponentBottomOneChoiceBinding
    private lateinit var type : String

    private val signUpViewModel : SignUpViewModel by activityViewModels()

    private val adapter : ComponentBottomOneChoiceAdapter by lazy {
        ComponentBottomOneChoiceAdapter(::onItemClicked, contents, selected)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.component_bottom_one_choice, container, false)

        initBinding()
        initComponent()
        initUi()

        return binding.root
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initComponent() {
        tag?.let { type = it }
    }

    private fun initUi() {
        validateWithType()
        initRv()
    }

    private fun validateWithType() {
        when(type) {
            "AREA" -> TYPE_AREA()
            "CITY" -> TYPE_CITY()
            "GRADUATION" -> TYPE_GRADUATION()
        }
    }

    private fun onItemClicked(text: String) {
        if(type == "AREA") signUpViewModel.setSelectedArea(text)
        else if(type == "CITY") signUpViewModel.setSelectedCity(text)
        else if(type == "GRADUATION") signUpViewModel.setSelectedGraduation(text)
        dismiss()
    }

    private fun initRv() {
        binding.componentBottomOneChoiceRvContent.adapter = adapter
    }
    private fun TYPE_AREA() {
        binding.componentBottomOneChoiceTvTitle.text = "시/도"
    }

    private fun TYPE_CITY() {
        binding.componentBottomOneChoiceTvTitle.text = "시/군/구"
    }

    private fun TYPE_GRADUATION() {
        binding.componentBottomOneChoiceTvTitle.text = "학력"
    }
}