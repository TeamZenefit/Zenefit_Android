package com.zenefit.zenefit_android.data.remote.response

import com.zenefit.zenefit_android.presentation.util.BaseResponse

data class ResponseSignInData(
    val result: ResultSignInData
) : BaseResponse() {
    data class ResultSignInData(
        val userId : String,
        val accessToken: String,
        val refreshToken: String,
        val nickname : String
    )
}