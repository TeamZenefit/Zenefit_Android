package com.zenefit.zenefit_android.data.local.source

import android.system.Os.remove
import com.zenefit.zenefit_android.domain.source.SharedPreferenceSource
import com.zenefit.zenefit_android.presentation.Zenefit.Companion.mSharedPreference

class SharedPreferenceLocalSource : SharedPreferenceSource {

    override suspend fun saveAccessToken(accessToken : String?, refreshToken : String?) {
        try {
            mSharedPreference.edit().apply{
                accessToken?.let { putString("jwt", it).apply() }
                refreshToken?.let { putString("refresh", it).apply() }
            }.commit()
        } catch (exception : Exception) {
            throw exception
        }
    }

    override suspend fun saveUserId(userId: String) {
        try {
            mSharedPreference.edit().apply {
                putString("userId", userId).apply()
            }.commit()
        } catch (exception : Exception) {
            throw exception
        }
    }

    override suspend fun getUserId(): String {
        return mSharedPreference.getString("userId", "NULL") ?: "NULL"
    }

    override suspend fun deleteUserJWT() {
        mSharedPreference.edit().clear().apply()
    }

    override suspend fun initUserData() {
        try {
            mSharedPreference.edit().apply {
                remove("jwt")
                remove("userId")
            }.commit()
        } catch (exception : Exception) {
            throw exception
        }
    }
}