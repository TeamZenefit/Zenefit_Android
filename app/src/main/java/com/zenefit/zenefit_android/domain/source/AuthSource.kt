package com.zenefit.zenefit_android.domain.source

import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData

interface AuthSource {

    suspend fun requestSignIn(body : RequestSignInData) : Result<ResponseSignInData>
}