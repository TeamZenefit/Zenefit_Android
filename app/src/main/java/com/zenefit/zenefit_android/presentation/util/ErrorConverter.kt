package com.zenefit.zenefit_android.presentation.util

import android.util.Log

import okhttp3.ResponseBody

object ErrorConverter {
    fun ResponseBody.convertAndGetCode() : Int {
        Log.e("----", "convertAndGetCode: ${this.string()}", )
        return 0
    }
}