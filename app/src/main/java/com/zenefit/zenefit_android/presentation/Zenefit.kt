package com.zenefit.zenefit_android.presentation

import android.app.Application
import android.content.SharedPreferences
import com.kakao.sdk.common.KakaoSdk
import com.zenefit.zenefit_android.BuildConfig
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Zenefit : Application() {

    companion object {
        lateinit var mSharedPreference : SharedPreferences

    }

    override fun onCreate() {
        super.onCreate()

        mSharedPreference = applicationContext.getSharedPreferences("Zenefit", MODE_PRIVATE)

        initKakaoSDK()
    }

    private fun initKakaoSDK() {
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_KEY)
    }

}