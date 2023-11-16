package com.zenefit.zenefit_android.presentation.util

import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.toSpannable
import androidx.core.view.marginStart
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.zenefit.zenefit_android.R


@BindingAdapter("common_set_image")
fun ImageView.setImage(path : Any?) {
    Glide.with(this).load(path).into(this)
}

@BindingAdapter("circle_set_image")
fun ImageView.setCircleImage(path : Any?) {
    Glide.with(this).load(path).circleCrop().into(this)
}

@BindingAdapter("main_title_margin_start", "dimen_not_detail", "dimen_detail")
fun View.setMarginStart(isDetail : Boolean, not_detail : Float, detail : Float) {
    (this.layoutParams as ViewGroup.MarginLayoutParams).let {
        it.marginStart = if (isDetail) detail.toInt() else not_detail.toInt()
        this.layoutParams = it
    }
}