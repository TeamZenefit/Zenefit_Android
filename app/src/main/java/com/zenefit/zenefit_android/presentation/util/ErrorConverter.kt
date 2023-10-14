package com.zenefit.zenefit_android.presentation.util

import android.util.Log
import com.google.gson.Gson
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData

import okhttp3.ResponseBody

object ErrorConverter {
    fun ResponseBody.convertError() : Int {
        return 0
    }
}