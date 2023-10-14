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
import com.zenefit.zenefit_android.databinding.ComponentBottomMultiChoiceBinding
import com.zenefit.zenefit_android.presentation.component.adapter.ComponentBottomMultiChoiceAdapter
import com.zenefit.zenefit_android.presentation.ui.sign_up.viewmodel.SignUpViewModel

class ComponentBottomMultipleChoice(private val contents : List<String>,
                                    private val selected : List<String>?) : BottomSheetDialogFragment() {
    private lateinit var binding : ComponentBottomMultiChoiceBinding
    private lateinit var type : String

    private var mSelected = selected?.toMutableList() ?: mutableListOf()

    private val signUpViewModel : SignUpViewModel by activityViewModels()

    private val contentAdapter : ComponentBottomMultiChoiceAdapter by lazy {
        ComponentBottomMultiChoiceAdapter(::onItemClicked, contents)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.component_bottom_multi_choice, container, false)

        initBinding()
        initComponent()
        initUi()

        checkFinishEnabled() //재선택시 체크용
        return binding.root
    }

    private fun initBinding() {
        binding.dialog = this
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
            "JOB" -> TYPE_JOB()
        }
    }

    fun onFinishClicked() {
        if (type == "JOB") mSelected.let { signUpViewModel.setSelectedJob(it) }
        dismiss()
    }

    private fun onItemClicked(text: String) {
        if(mSelected.isEmpty() || text !in mSelected) mSelected.add(text)
        else mSelected.remove(text)

        checkFinishEnabled()
    }

    private fun checkFinishEnabled() {
        binding.componentBottomMultiChoiceBtnFinish.isEnabled = mSelected.isNotEmpty()
    }

    private fun initRv() {
        binding.componentBottomMultiChoiceRvContent.apply {
            adapter = contentAdapter
            contentAdapter.selected = mSelected
            itemAnimator = null
        }
    }
    private fun TYPE_JOB() {
        binding.componentBottomMultiChoiceTvTitle.text = "직업"
    }
}