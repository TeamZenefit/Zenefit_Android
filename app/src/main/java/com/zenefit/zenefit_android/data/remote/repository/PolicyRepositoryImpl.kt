package com.zenefit.zenefit_android.data.remote.repository

import com.zenefit.zenefit_android.data.remote.response.ResponsePolicyCountData
import com.zenefit.zenefit_android.domain.repository.PolicyRepository
import com.zenefit.zenefit_android.domain.source.PolicySource
import javax.inject.Inject

class PolicyRepositoryImpl @Inject constructor(private val source : PolicySource): PolicyRepository {
    override suspend fun requestPolicyCount(): Result<ResponsePolicyCountData.ResultPolicyCountData> {
        return source.requestPolicyCount()
    }

}