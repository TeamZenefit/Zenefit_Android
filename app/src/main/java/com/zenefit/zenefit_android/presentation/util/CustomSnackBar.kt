package com.zenefit.zenefit_android.presentation.util

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ComponentToastBinding

object CustomSnackBar {

    fun makeSnackBar(view: View, message: String): Snackbar {
        val snackBar = Snackbar.make(view, message, 2000)

        val binding = DataBindingUtil.inflate<ComponentToastBinding>(
            LayoutInflater.from(view.context),
            R.layout.component_toast,
            view.parent as ViewGroup,
            false
        )

        binding.componentToastText.setTextColor(Color.WHITE)
        binding.componentToastText.text = when (message) {
            "ADD_CALENDAR" -> case_ADD_CALENDAR()
            else -> message
        }

        val params = snackBar.view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.BOTTOM
        snackBar.view.layoutParams = params


        val snackbarLayout = snackBar.view as Snackbar.SnackbarLayout
        snackbarLayout.setBackgroundColor(Color.TRANSPARENT)
        snackbarLayout.removeAllViews()
        snackbarLayout.addView(binding.root)

        return snackBar
    }

    private fun case_ADD_CALENDAR() = "달력에 추가되었습니다"
}