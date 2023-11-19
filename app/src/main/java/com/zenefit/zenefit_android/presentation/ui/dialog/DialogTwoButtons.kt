package com.zenefit.zenefit_android.presentation.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.DialogTwoButtonsBinding

class DialogTwoButtons(
    private val policyId : Int?,
    private val onPositiveClicked : (Int?) -> Unit
) : DialogFragment() {
    private lateinit var binding : DialogTwoButtonsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_two_buttons, container, false)

        initUi()

        return binding.root
    }

    private fun initUi() {
        this.tag?.validateWithType()
        initPositiveClicked(policyId)
    }

    private fun initPositiveClicked(policyId: Int?) {
        binding.dialogTwoButtonTvPositive.setOnClickListener {
            onPositiveClicked.invoke(policyId)
            dismiss()
        }
    }

    private fun String.validateWithType() {
        when(this) {
            "REMOVE_INTEREST_POLICY" -> case_REMOVE_INTEREST_POLICY()
            "REMOVE_BENEFIT_POLICY" -> case_REMOVE_BENEFIT_POLICY()
        }
    }

    private fun case_REMOVE_INTEREST_POLICY() {
        binding.dialogTwoButtonTvTitle.text = "관심 정책을 삭제할까요?"
        binding.dialogTwoButtonTvSubtitle.text = "스크랩한 정책이 달력에서도 제거돼요!"
        binding.dialogTwoButtonTvNegative.text = "아니오"

        binding.dialogTwoButtonTvPositive.apply{
            text = "삭제하기"
            setTextColor(resources.getColor(R.color.white, null))
            background = resources.getDrawable(R.drawable.bg_alert_99, null)
        }
    }

    private fun case_REMOVE_BENEFIT_POLICY() {
        binding.dialogTwoButtonTvTitle.text = "수혜 정책을 삭제할까요?"
        binding.dialogTwoButtonTvSubtitle.text = "삭제하면 정책이 다시 추천될 수도 있어요"
        binding.dialogTwoButtonTvNegative.text = "아니오"

        binding.dialogTwoButtonTvPositive.apply{
            text = "삭제하기"
            setTextColor(resources.getColor(R.color.white, null))
            background = resources.getDrawable(R.drawable.bg_alert_99, null)
        }
    }

    override fun onResume() {
        super.onResume()
        val windowManager = requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.75).toInt()
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams

        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}