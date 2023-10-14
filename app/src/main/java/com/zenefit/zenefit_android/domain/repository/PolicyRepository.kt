package com.zenefit.zenefit_android.domain.repository

import com.zenefit.zenefit_android.data.remote.response.ResponsePolicyCountData

interface PolicyRepository {

    suspend fun requestPolicyCount() : Result<ResponsePolicyCountData.ResultPolicyCountData>
}