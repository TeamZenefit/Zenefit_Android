package com.zenefit.zenefit_android.data.remote.response

import com.zenefit.zenefit_android.presentation.util.BaseResponse

data class ResponseUserPolicyCountData(
    val result : ResultUserPolicyCountData
) : BaseResponse() {
    data class ResultUserPolicyCountData(
        val size : Int
    )
}
