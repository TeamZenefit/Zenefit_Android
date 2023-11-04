package com.zenefit.zenefit_android.data.remote.response

import com.zenefit.zenefit_android.presentation.util.BaseResponse

data class ResponseHomeData (
    val result : ResultHomeData
) : BaseResponse() {

    data class ResultHomeData(
        val nickname : String,
        val userImage : String,
        val benefit : Int,
        val interestPolicyCnt : Int,
        val applyPolicyCnt : Int,
        val recommendPolicy : List<ResponseHomePolicyListData>?,
        val endDatePolicy : List<ResponseHomePolicyListData>?
    )
    data class ResponseHomePolicyListData(
        val policyName : String,
        val supportPolicyType : String,
        val supportPolicyTypeName : String,

        var endDate : String
    )
}