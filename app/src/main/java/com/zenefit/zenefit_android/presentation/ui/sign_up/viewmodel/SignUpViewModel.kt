package com.zenefit.zenefit_android.presentation.ui.sign_up.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    private val _currentSignUpLevel = MutableLiveData<Int>(1)
    val currentSignUpLevel : LiveData<Int> = _currentSignUpLevel

    private val _currentSignUpLevelText = MutableLiveData<String>("기본 정보")
    val currentSignUpLevelText : LiveData<String> = _currentSignUpLevelText

    private val _signUpTitleText = MutableLiveData<String>("나이와 사는 곳을 알려주세요!")
    val signUpTitleText : LiveData<String> = _signUpTitleText

    fun setTextByLevel(level : Int) {
        _currentSignUpLevelText.value = getCurrentLevelText[level - 1]
        _signUpTitleText.value = getCurrentTitleText[level - 1]
    }

    private val getCurrentLevelText = listOf("기본 정보", "소득 기재", "상세 정보", "약관 동의")
    private val getCurrentTitleText = listOf("나이와 사는 곳을 알려주세요!", "소득을 알려주세요!", "학력과 직업을 입력해주세요", "회원님의 약관동의가 필요해요")
}