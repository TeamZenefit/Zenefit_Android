package com.zenefit.zenefit_android.domain.repository

interface SharedPreferenceRepository {
    suspend fun saveAccessToken(accessToken : String?, refreshToken : String?)
    suspend fun getUserId() : String
}