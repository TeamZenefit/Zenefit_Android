package com.zenefit.zenefit_android.domain.source

import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignUpData
import com.zenefit.zenefit_android.data.remote.response.ResponseHomeData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseUserPolicyCountData

interface UserSource {

    suspend fun requestAreaData() : Result<List<String>>

    suspend fun requestCityData(area : String) : Result<List<String>>

    suspend fun requestSignUp(body : RequestSignUpData) : Result<ResponseSignInData>

    suspend fun requestHomeData() : Result<ResponseHomeData.ResultHomeData>

    suspend fun requestInterestPolicyCount() : Result<ResponseUserPolicyCountData.ResultUserPolicyCountData>

    suspend fun requestBenefitPolicyCount() : Result<ResponseUserPolicyCountData.ResultUserPolicyCountData>
}