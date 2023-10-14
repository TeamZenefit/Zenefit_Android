package com.zenefit.zenefit_android.data.remote.repository

import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import com.zenefit.zenefit_android.domain.repository.AuthRepository
import com.zenefit.zenefit_android.domain.source.AuthSource
import com.zenefit.zenefit_android.domain.source.SharedPreferenceSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val source: AuthSource,
    private val sharedPreferenceSource: SharedPreferenceSource
) : AuthRepository {
    override suspend fun requestSignIn(body: RequestSignInData): Result<ResponseSignInData> {
        return source.requestSignIn(body)
    }

    override suspend fun saveUserId(userId: String) {
        sharedPreferenceSource.saveUserId(userId)
    }
}