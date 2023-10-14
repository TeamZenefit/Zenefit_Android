package com.zenefit.zenefit_android.data.local.repository

import com.zenefit.zenefit_android.domain.repository.SharedPreferenceRepository
import com.zenefit.zenefit_android.domain.source.SharedPreferenceSource
import javax.inject.Inject

class SharedPreferenceLocalRepositoryImpl @Inject constructor(private val sharedPreferenceSource: SharedPreferenceSource): SharedPreferenceRepository {
    override suspend fun saveAccessToken(accessToken: String?, refreshToken: String?) {
        return sharedPreferenceSource.saveAccessToken(accessToken, refreshToken)
    }

    override suspend fun getUserId(): String {
        return sharedPreferenceSource.getUserId()
    }
}