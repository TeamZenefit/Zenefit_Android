package com.zenefit.zenefit_android.data.remote.source

import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignUpData
import com.zenefit.zenefit_android.data.remote.response.ResponseHomeData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseUserPolicyCountData
import com.zenefit.zenefit_android.data.remote.service.UserService
import com.zenefit.zenefit_android.domain.source.UserSource
import com.zenefit.zenefit_android.presentation.util.ErrorConverter.convertError
import javax.inject.Inject

class UserRemoteSource @Inject constructor(private val service : UserService): UserSource {
    override suspend fun requestAreaData(): Result<List<String>> {
        val res = service.requestAreaData()
        return when(res.code()) {
            in 200..399 -> Result.success(res.body()!!.result)
            else -> Result.failure(IllegalArgumentException(res.errorBody()?.convertError().toString()))
        }
    }

    override suspend fun requestCityData(area : String): Result<List<String>> {
        val res = service.requestCityData(area)
        return when(res.code()) {
            in 200..399 -> Result.success(res.body()!!.result)
            else -> Result.failure(IllegalArgumentException(res.errorBody()?.convertError().toString()))
        }
    }

    override suspend fun requestSignUp(body: RequestSignUpData): Result<ResponseSignInData> {
        val res = service.requestSignUp(body)
        return when(res.code()) {
            in 200..399 -> Result.success(res.body()!!)
            else -> Result.failure(IllegalArgumentException(res.errorBody()?.string()))
        }
    }

    override suspend fun requestHomeData(): Result<ResponseHomeData.ResultHomeData> {
        val res = service.requestHomeData()
        return when(res.code()) {
            in 200..399 -> Result.success(res.body()!!.result)
            else -> Result.failure(IllegalArgumentException(res.errorBody()?.string()))
        }
    }

    override suspend fun requestInterestPolicyCount(): Result<ResponseUserPolicyCountData.ResultUserPolicyCountData> {
        val res = service.requestInterestPolicyCountData()
        return when(res.code()) {
            in 200..399 -> Result.success(res.body()!!.result)
            else -> Result.failure(IllegalArgumentException(res.errorBody()?.string()))
        }
    }

    override suspend fun requestBenefitPolicyCount(): Result<ResponseUserPolicyCountData.ResultUserPolicyCountData> {
        val res = service.requestBenefitPolicyCountData()
        return when(res.code()) {
            in 200..399 -> Result.success(res.body()!!.result)
            else -> Result.failure(IllegalArgumentException(res.errorBody()?.string()))
        }
    }
}