package com.zenefit.zenefit_android.presentation.ui.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignInData
import com.zenefit.zenefit_android.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    private val _loginCode = MutableLiveData<Int>()
    val loginCode : LiveData<Int> = _loginCode
    fun requestSignIn(token : String) {
        viewModelScope.launch {
            authRepository.requestSignIn(RequestSignInData("KAKAO", token))
                .onSuccess { _loginCode.value = it.code }
                .onFailure { Log.e("----", "requestSignIn: ${it.message}", ) }
        }
    }


}