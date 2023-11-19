package com.zenefit.zenefit_android.data.remote.service

import com.zenefit.zenefit_android.data.remote.response.ResponsePolicyCountData
import retrofit2.Response
import retrofit2.http.GET

interface PolicyService {

    @GET("/policy/recommend/count")
    suspend fun requestPolicyCount() : Response<ResponsePolicyCountData>



}