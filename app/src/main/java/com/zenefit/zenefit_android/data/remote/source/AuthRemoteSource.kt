package com.zenefit.zenefit_android.data.remote.source

import com.zenefit.zenefit_android.presentation.util.ErrorConverter.convertError
import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import com.zenefit.zenefit_android.data.remote.service.AuthService
import com.zenefit.zenefit_android.domain.source.AuthSource
import javax.inject.Inject

class AuthRemoteSource @Inject constructor(private val service : AuthService): AuthSource {
    override suspend fun requestSignIn(body: RequestSignInData): Result<ResponseSignInData> {
        val res = service.requestSignIn(body)
        return when(res.code()) {
            in 200..399 -> Result.success(res.body()!!)
            else -> Result.failure(IllegalArgumentException(res.errorBody()?.string()))
        }
    }
}