package com.zenefit.zenefit_android.data.remote.repository

import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignUpData
import com.zenefit.zenefit_android.data.remote.response.ResponseHomeData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseUserPolicyCountData
import com.zenefit.zenefit_android.domain.repository.UserRepository
import com.zenefit.zenefit_android.domain.source.UserSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val source : UserSource): UserRepository {
    override suspend fun requestAreaData(): Result<List<String>> {
        return source.requestAreaData()
    }

    override suspend fun requestCityData(area : String): Result<List<String>> {
        return source.requestCityData(area)
    }

    override suspend fun requestSignUp(body: RequestSignUpData): Result<ResponseSignInData> {
        return source.requestSignUp(body)
    }

    override suspend fun requestHomeData(): Result<ResponseHomeData.ResultHomeData> {
        return source.requestHomeData()
    }

    override suspend fun requestInterestPolicyCount(): Result<ResponseUserPolicyCountData.ResultUserPolicyCountData> {
        return source.requestInterestPolicyCount()
    }

    override suspend fun requestBenefitPolicyCount(): Result<ResponseUserPolicyCountData.ResultUserPolicyCountData> {
        return source.requestBenefitPolicyCount()
    }
}