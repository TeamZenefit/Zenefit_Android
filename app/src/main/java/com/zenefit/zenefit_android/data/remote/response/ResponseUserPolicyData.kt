package com.zenefit.zenefit_android.data.remote.response

import com.zenefit.zenefit_android.presentation.util.BaseResponse

data class ResponseUserPolicyData (
    val result : ContentUserPolicyData
) : BaseResponse() {

    data class ContentUserPolicyData(
        val content : List<ResultUserPolicyData>
    )
    data class ResultUserPolicyData(
        val policyId : Int,
        val policyName : String,
        val policyIntroduction : String,
        val policyLogo : String,
        val applyEndDate : String?,
        val benefit : Int?,
        val supportPolicyType : String?,
        val supportPolicyTypeDescription : String?
    )
}