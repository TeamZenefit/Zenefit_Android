package com.zenefit.zenefit_android.data.remote.repository

import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import com.zenefit.zenefit_android.domain.repository.AuthRepository
import com.zenefit.zenefit_android.domain.source.AuthSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val source : AuthSource): AuthRepository {
    override suspend fun requestSignIn(body: RequestSignInData): Result<ResponseSignInData> {
        return source.requestSignIn(body)
    }
}