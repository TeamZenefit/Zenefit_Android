package com.zenefit.zenefit_android.presentation.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ComponentTextBinding

class ComponentTextView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private lateinit var binding: ComponentTextBinding
    private lateinit var type: String

    private var status = false

    init {
        initBinding()
    }

    fun initComponent(type: String, onClick: (String) -> Unit) {
        this.type = type

        initUi(onClick)
    }

    fun setSelectedText(text : String) {
        if(text.isEmpty()) validateWithType() else binding.componentTextTvContent.text = text
    }

    private fun initBinding() {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.component_text,
            this,
            false
        )
        addView(binding.root)
    }

    private fun initUi(onClick: (String) -> Unit) {
        validateWithType()
        validateFocus()
        setClickEvent(onClick)
        binding.root.isFocusableInTouchMode = true
    }

    private fun setClickEvent(onClick: (String) -> Unit) {
        binding.root.setOnTouchListener { _, motionEvent ->
            performClick()
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                binding.root.requestFocus()
                onClick.invoke(type)
            }

            return@setOnTouchListener true
        }
    }

    private fun validateFocus() {
        binding.root.setOnFocusChangeListener { _, b ->
            if (b) setWithFocus() else setWithoutFocus()
        }

        binding.componentTextTvContent.addTextChangedListener {
            checkStatus(it.toString())
        }
    }

    private fun setWithFocus() {
        binding.apply {
            componentTextTvTitle.setTextColor(resources.getColor(R.color.primary_normal, null))
            componentTextViewTextStroke.setBackgroundColor(
                resources.getColor(
                    R.color.primary_normal,
                    null
                )
            )
            componentTextTvContent.setTextColor(resources.getColor(R.color.text_normal, null))

            componentTextTvAdditional.visibility = View.VISIBLE
        }
    }

    private fun checkStatus(content: String) {
        status = when (type) {
            "AREA" -> content != "시/도"
            "CITY" -> content != "시/군/구"
            "GRADUATION" -> content != "학력"
            "JOB" -> content != "직업"
            else -> false
        }

        setWithoutFocus()
    }


    private fun setWithoutFocus() {
        if (status) setAlternative() else setDisable()
    }

    private fun setDisable() {
        binding.apply {
            componentTextTvTitle.setTextColor(resources.getColor(R.color.text_disable, null))
            componentTextViewTextStroke.setBackgroundColor(resources.getColor(R.color.line_disable, null))
            componentTextTvContent.setTextColor(resources.getColor(R.color.text_disable, null))

            componentTextTvAdditional.visibility = View.INVISIBLE
        }
    }

    private fun setAlternative() {
        binding.apply {
            componentTextTvTitle.setTextColor(resources.getColor(R.color.text_alternative, null))
            componentTextViewTextStroke.setBackgroundColor(resources.getColor(R.color.text_alternative, null))
            componentTextTvContent.setTextColor(resources.getColor(R.color.text_alternative, null))

            componentTextTvAdditional.visibility = View.INVISIBLE
        }
    }

    private fun validateWithType() {
        when (type) {
            "AREA" -> TYPE_AREA()
            "CITY" -> TYPE_CITY()
            "GRADUATION" -> TYPE_GRADUATION()
            "JOB" -> TYPE_JOB()
        }
    }

    private fun TYPE_AREA() {
        binding.componentTextTvTitle.text = "시/도"
        binding.componentTextTvContent.text = "시/도"
        binding.componentTextTvAdditional.text = "등본상 거주지를 입력하세요"
    }

    private fun TYPE_CITY() {
        binding.componentTextTvTitle.text = "시/군/구"
        binding.componentTextTvContent.text = "시/군/구"
        binding.componentTextTvAdditional.text = "등본상 거주지를 입력하세요"
    }

    private fun TYPE_GRADUATION() {
        binding.componentTextTvTitle.text = "학력"
        binding.componentTextTvContent.text = "학력"
        binding.componentTextTvAdditional.text = "현재 학력을 선택해주세요"
    }

    private fun TYPE_JOB() {
        binding.componentTextTvTitle.text = "직업"
        binding.componentTextTvContent.text = "직업"
        binding.componentTextTvAdditional.text = "현재 직업을 선택해주세요"
    }
}