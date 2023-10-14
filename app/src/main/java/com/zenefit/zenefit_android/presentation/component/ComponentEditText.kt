package com.zenefit.zenefit_android.presentation.component

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.text.Spanned
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ComponentEditTextBinding

class ComponentEditText(context : Context, attrs : AttributeSet) : ConstraintLayout(context, attrs){
    private lateinit var binding : ComponentEditTextBinding
    private lateinit var type : String

    private var earnText = ""
    private var seenEarnText = ""
    init {
        initBinding()
    }

    fun initComponent(type : String) {
        this.type = type

        initUi()
        initBindingParams()
    }

    fun setTextWatcher(onTextChanged : (String) -> Unit) {
        binding.componentEditEdtContent.addTextChangedListener {
            try {
                if(!it.isNullOrEmpty() && it.toString().toInt() < 1) binding.componentEditEdtContent.text = null
            } catch (exception : Exception) {
                exception.stackTrace
            }
            onTextChanged.invoke(if(type == "EARN") earnText else it.toString())
        }
    }
    private fun initBinding() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.component_edit_text, this, false)

        addView(binding.root)
    }

    private fun initBindingParams() {
        binding.type = type
    }

    private fun initUi() {
        validateWithType()
        validateFocus()
    }

    private fun validateFocus() {
        binding.componentEditEdtContent.setOnFocusChangeListener { _, b ->
            if(b) setWithFocus() else setWithoutFocus()
        }
    }

    private fun setWithFocus() {
        binding.apply {
            componentEditTvTitle.setTextColor(resources.getColor(R.color.primary_normal, null))
            componentEditViewEditStroke.setBackgroundColor(resources.getColor(R.color.primary_normal, null))

            componentEditTvAdditional.visibility = View.VISIBLE

            componentEditEdtContent.setTextColor(resources.getColor(R.color.text_normal, null))

            if (type =="EARN") binding.componentEditEdtContent.setText(earnText)
        }
    }

    private fun setWithoutFocus() {
        if(binding.componentEditEdtContent.text.isNullOrEmpty()) setDisable() else setAlternative()
    }

    private fun setDisable() {
        binding.apply {
            componentEditTvTitle.setTextColor(resources.getColor(R.color.text_disable, null))
            componentEditViewEditStroke.setBackgroundColor(resources.getColor(R.color.line_disable, null))

            componentEditTvAdditional.visibility = View.INVISIBLE
        }
    }

    private fun setAlternative() {
        binding.apply {
            componentEditTvTitle.setTextColor(resources.getColor(R.color.text_alternative, null))
            componentEditViewEditStroke.setBackgroundColor(resources.getColor(R.color.text_alternative, null))

            componentEditEdtContent.setTextColor(resources.getColor(R.color.text_alternative, null))

            componentEditTvAdditional.visibility = View.INVISIBLE
        }

        if(type == "EARN") binding.componentEditEdtContent.apply {
            earnText = text.toString()
            seenEarnText = text.toString().convertToWon()
            setText(seenEarnText)
        }
    }

    private fun String.convertToWon() : String {
        val overTenThousand = this.toInt() / 10000
        val tenThousand = this.toInt() % 10000
        return if(length > 4) "${overTenThousand}억 ${if(tenThousand != 0) tenThousand else ""}" else this
    }

    fun returnFinishState() : Boolean {
        return !binding.componentEditEdtContent.text.isNullOrEmpty()
    }

    fun setFocus() {
        binding.componentEditEdtContent.isFocusableInTouchMode = true
        binding.componentEditEdtContent.requestFocus()
    }

    private fun validateWithType() {
        when(type) {
            "AGE" -> TYPE_AGE()
            "EARN" -> TYPE_EARN()
        }
    }

    private fun TYPE_AGE() {
        binding.componentEditTvTitle.text = "나이"
        binding.componentEditEdtContent.apply{
            hint = "나이"
            inputType = InputType.TYPE_CLASS_NUMBER
            filters = arrayOf(InputFilter.LengthFilter(2))

        }
        binding.componentEditTvAdditional.text = "만 나이를 입력하세요"
    }

    private fun TYPE_EARN() {
        binding.componentEditTvTitle.text = "작년 소득"
        binding.componentEditEdtContent.apply{
            hint = "0"
            inputType = InputType.TYPE_CLASS_NUMBER
            filters = arrayOf(InputFilter.LengthFilter(8))
        }
        binding.componentEditTvAdditional.text = "1년 기준으로 입력하세요"
    }
}