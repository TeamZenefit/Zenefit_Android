package com.zenefit.zenefit_android.presentation.util

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("success") val isSuccess: Boolean = false,
    @SerializedName("code") val code: Int = 0,
    @SerializedName("message") val message: String? = null
)
