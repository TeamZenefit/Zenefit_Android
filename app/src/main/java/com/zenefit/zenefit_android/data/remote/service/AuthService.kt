package com.zenefit.zenefit_android.data.remote.service

import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/auth/login")
    suspend fun requestSignIn(
        @Body body: RequestSignInData
    ) : Response<ResponseSignInData>
}