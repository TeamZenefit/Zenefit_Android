package com.zenefit.zenefit_android.data.remote.service

import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignUpData
import com.zenefit.zenefit_android.data.remote.response.ResponseAreaData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("/user/area")
    suspend fun requestAreaData() : Response<ResponseAreaData>

    @GET("/user/city")
    suspend fun requestCityData(
        @Query("area") area : String
    ) : Response<ResponseAreaData>

    @PATCH("/user/signup")
    suspend fun requestSignUp(
        @Body body : RequestSignUpData
    ) : Response<ResponseSignInData>
}