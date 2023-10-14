package com.zenefit.zenefit_android.presentation.ui.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignInData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import com.zenefit.zenefit_android.domain.repository.AuthRepository
import com.zenefit.zenefit_android.domain.repository.SharedPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository,
    private val authRepository: AuthRepository): ViewModel() {
    private val _loginCode = MutableLiveData<Int>()
    val loginCode : LiveData<Int> = _loginCode
    fun requestSignIn(token : String) {
        viewModelScope.launch {
            authRepository.requestSignIn(RequestSignInData("KAKAO", token))
                .onSuccess {
                    _loginCode.value = it.code
                    saveJwt(it.result.accessToken, it.result.refreshToken)
                }
                .onFailure {
                    val result = Gson().fromJson(it.message!!, ResponseSignInData::class.java)
                    _loginCode.value = result.code
                    validateAndSaveUserId(result)
                }
        }
    }

    private fun validateAndSaveUserId(data : ResponseSignInData) {
        if(data.code in 2001..2005) {
            viewModelScope.launch {
                authRepository.saveUserId(data.result.userId)
            }
        }
    }

    private fun saveJwt(accessToken : String, refreshToken : String) {
        viewModelScope.launch {
            sharedPreferenceRepository.saveAccessToken(accessToken, refreshToken)
        }
    }
}