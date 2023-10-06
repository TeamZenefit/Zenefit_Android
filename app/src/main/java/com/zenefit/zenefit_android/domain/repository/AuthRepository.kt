package com.zenefit.zenefit_android.domain.repository

import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData

interface AuthRepository {

    suspend fun requestSignIn(body : RequestSignInData) : Result<ResponseSignInData>
}