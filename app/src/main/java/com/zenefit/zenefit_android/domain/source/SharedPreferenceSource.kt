package com.zenefit.zenefit_android.domain.source

interface SharedPreferenceSource {

    suspend fun saveAccessToken(accessToken : String?, refreshToken : String?)

    suspend fun saveUserId(userId : String)

    suspend fun getUserId() : String

    suspend fun deleteUserJWT()

    suspend fun initUserData()

}