package com.zenefit.zenefit_android.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("common_set_image")
fun ImageView.setImage(path : Any?) {
    Glide.with(this).load(path).into(this)
}