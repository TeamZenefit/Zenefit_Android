package com.zenefit.zenefit_android.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("common_set_image")
fun ImageView.setImage(path : Any?) {
    Glide.with(this).load(path).into(this)
}

@BindingAdapter("circle_set_image")
fun ImageView.setCircleImage(path : Any?) {
    Glide.with(this).load(path).circleCrop().into(this)
}