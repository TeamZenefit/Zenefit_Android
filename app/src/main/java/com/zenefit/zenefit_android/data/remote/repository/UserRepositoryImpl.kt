package com.zenefit.zenefit_android.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignUpData
import com.zenefit.zenefit_android.data.remote.response.ResponseHomeData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseUserPolicyCountData
import com.zenefit.zenefit_android.data.remote.response.ResponseUserPolicyData
import com.zenefit.zenefit_android.data.remote.service.UserService
import com.zenefit.zenefit_android.data.remote.source.UserBenefitPolicyPagingSource
import com.zenefit.zenefit_android.data.remote.source.UserInterestPolicyPagingSource
import com.zenefit.zenefit_android.domain.repository.UserRepository
import com.zenefit.zenefit_android.domain.source.UserSource
import com.zenefit.zenefit_android.presentation.util.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service : UserService,
    private val source : UserSource): UserRepository {
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

    override fun requestInterestPolicy(): Flow<PagingData<ResponseUserPolicyData.ResultUserPolicyData>> {
        return Pager(PagingConfig(pageSize = 10)) {
            UserInterestPolicyPagingSource(Dispatchers.IO, service)
        }.flow
    }

    override suspend fun requestInterestPolicyCount(): Result<ResponseUserPolicyCountData.ResultUserPolicyCountData> {
        return source.requestInterestPolicyCount()
    }

    override suspend fun requestDeleteInterestPolicy(policyId: Int): Result<BaseResponse> {
        return source.requestDeleteInterestPolicy(policyId)
    }

    override fun requestBenefitPolicy(): Flow<PagingData<ResponseUserPolicyData.ResultUserPolicyData>> {
        return Pager(PagingConfig(pageSize = 10)) {
            UserBenefitPolicyPagingSource(Dispatchers.IO, service)
        }.flow
    }

    override suspend fun requestBenefitPolicyCount(): Result<ResponseUserPolicyCountData.ResultUserPolicyCountData> {
        return source.requestBenefitPolicyCount()
    }

    override suspend fun requestDeleteBenefitPolicy(policyId: Int): Result<BaseResponse> {
        return source.requestDeleteBenefitPolicy(policyId)
    }
}