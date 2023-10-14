package com.zenefit.zenefit_android.domain.source

import com.zenefit.zenefit_android.data.remote.response.ResponsePolicyCountData

interface PolicySource {

    suspend fun requestPolicyCount() : Result<ResponsePolicyCountData.ResultPolicyCountData>
}