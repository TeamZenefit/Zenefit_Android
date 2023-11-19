package com.zenefit.zenefit_android.data.remote.service

import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignUpData
import com.zenefit.zenefit_android.data.remote.response.ResponseAreaData
import com.zenefit.zenefit_android.data.remote.response.ResponseHomeData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseUserPolicyCountData
import com.zenefit.zenefit_android.data.remote.response.ResponseUserPolicyData
import com.zenefit.zenefit_android.presentation.util.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
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

    @GET("/user/home")
    suspend fun requestHomeData() : Response<ResponseHomeData>

    @GET("/user/policy")
    suspend fun requestInterestPolicyData(
        @Query("page") page : Int,
        @Query("size") size : Int
    ) : ResponseUserPolicyData

    @GET("/user/policy/size")
    suspend fun requestInterestPolicyCountData() : Response<ResponseUserPolicyCountData>

    @GET("/user/policy/apply")
    suspend fun requestBenefitPolicyData(
        @Query("page") page : Int,
        @Query("size") size : Int
    ) : ResponseUserPolicyData

    @GET("/user/policy/apply/size")
    suspend fun requestBenefitPolicyCountData() : Response<ResponseUserPolicyCountData>

    @DELETE("/user/policy/{policyId}")
    suspend fun requestDeleteInterestPolicy(
        @Path("policyId") policyId : Int
    ) : Response<BaseResponse>

    @DELETE("/user/policy/apply/{policyId}")
    suspend fun requestDeleteBenefitPolicy(
        @Path("policyId") policyId : Int
    ) : Response<BaseResponse>
}