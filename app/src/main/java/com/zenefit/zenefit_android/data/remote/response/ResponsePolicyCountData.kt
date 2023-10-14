package com.zenefit.zenefit_android.data.remote.response

import com.zenefit.zenefit_android.presentation.util.BaseResponse

data class ResponsePolicyCountData(
    val result : ResultPolicyCountData
) : BaseResponse() {
    data class ResultPolicyCountData(
        val nickname : String,
        val policyCnt : Int
    )
}
