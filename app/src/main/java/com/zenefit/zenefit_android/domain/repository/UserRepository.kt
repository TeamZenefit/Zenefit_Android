package com.zenefit.zenefit_android.domain.repository

import androidx.paging.PagingData
import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignUpData
import com.zenefit.zenefit_android.data.remote.response.ResponseHomeData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseUserPolicyCountData
import com.zenefit.zenefit_android.data.remote.response.ResponseUserPolicyData
import com.zenefit.zenefit_android.presentation.util.BaseResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun requestAreaData() : Result<List<String>>

    suspend fun requestCityData(area : String) : Result<List<String>>

    suspend fun requestSignUp(body : RequestSignUpData) : Result<ResponseSignInData>

    suspend fun requestHomeData() : Result<ResponseHomeData.ResultHomeData>

    fun requestInterestPolicy() : Flow<PagingData<ResponseUserPolicyData.ResultUserPolicyData>>

    suspend fun requestInterestPolicyCount() : Result<ResponseUserPolicyCountData.ResultUserPolicyCountData>

    suspend fun requestDeleteInterestPolicy(policyId : Int) : Result<BaseResponse>

    fun requestBenefitPolicy() : Flow<PagingData<ResponseUserPolicyData.ResultUserPolicyData>>

    suspend fun requestBenefitPolicyCount() : Result<ResponseUserPolicyCountData.ResultUserPolicyCountData>

    suspend fun requestDeleteBenefitPolicy(policyId : Int) : Result<BaseResponse>
}