package com.zenefit.zenefit_android.domain.source

import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignUpData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData

interface UserSource {

    suspend fun requestAreaData() : Result<List<String>>

    suspend fun requestCityData(area : String) : Result<List<String>>

    suspend fun requestSignUp(body : RequestSignUpData) : Result<ResponseSignInData>
}