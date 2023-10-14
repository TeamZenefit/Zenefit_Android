package com.zenefit.zenefit_android.data.remote.source

import com.zenefit.zenefit_android.data.remote.response.ResponsePolicyCountData
import com.zenefit.zenefit_android.data.remote.service.PolicyService
import com.zenefit.zenefit_android.domain.source.PolicySource
import javax.inject.Inject

class PolicyRemoteSource @Inject constructor(private val service : PolicyService): PolicySource{
    override suspend fun requestPolicyCount(): Result<ResponsePolicyCountData.ResultPolicyCountData> {
        val res = service.requestPolicyCount()

        return if(res.isSuccessful) Result.success(res.body()!!.result)
        else Result.failure(IllegalArgumentException(res.errorBody()!!.string()))
    }

}
