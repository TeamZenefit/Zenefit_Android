package com.zenefit.zenefit_android.presentation.ui.sign_up.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.zenefit.zenefit_android.data.data.Terms
import com.zenefit.zenefit_android.data.remote.reqeust.RequestSignUpData
import com.zenefit.zenefit_android.data.remote.response.ResponseSignInData
import com.zenefit.zenefit_android.domain.repository.SharedPreferenceRepository
import com.zenefit.zenefit_android.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository,
    private val userRepository: UserRepository): ViewModel() {

    private val _currentSignUpLevel = MutableLiveData<Int>(1)
    val currentSignUpLevel : LiveData<Int> = _currentSignUpLevel

    private val _saveButtonEnabled = MutableLiveData(false)
    val saveButtonEnabled : LiveData<Boolean> = _saveButtonEnabled

    private val _termsSaveButtonEnabled = MutableLiveData(false)
    val termsSaveButtonEnabled : LiveData<Boolean> = _termsSaveButtonEnabled

    private val _currentSignUpLevelText = MutableLiveData<String>("기본 정보")
    val currentSignUpLevelText : LiveData<String> = _currentSignUpLevelText

    private val _signUpTitleText = MutableLiveData<String>("나이와 사는 곳을 알려주세요!")
    val signUpTitleText : LiveData<String> = _signUpTitleText

    private val _areaData = MutableLiveData<MutableList<String>>()
    val areaData : LiveData<MutableList<String>> = _areaData

    private val _cityData = MutableLiveData<MutableList<String>>()
    val cityData : LiveData<MutableList<String>> = _cityData

    private val _selectedArea = MutableLiveData<String>()
    val selectedArea : LiveData<String> = _selectedArea

    private val _selectedCity = MutableLiveData<String>()
    val selectedCity : LiveData<String> = _selectedCity

    private val _selectedGraduation = MutableLiveData<String>()
    val selectedGraduation : LiveData<String> = _selectedGraduation

    private val _selectedJob = MutableLiveData<MutableList<String>>()
    val selectedJob : LiveData<MutableList<String>> = _selectedJob

    private val _focusRoute = mutableListOf<Boolean>().apply { for(i in 0 until 6) add(false) }
    val focusRoute : MutableList<Boolean> = _focusRoute

    private val _allCheckedState = MutableLiveData<Boolean>(false)
    val allCheckedState : LiveData<Boolean> = _allCheckedState

    private val _termsStateList = MutableLiveData<MutableList<Terms>>().also { it.value = getTermsData() }
    val termsStateList : LiveData<MutableList<Terms>> = _termsStateList

    private val _userAge = MutableLiveData<String>()
    val userAge : LiveData<String> = _userAge

    private val _userEarn = MutableLiveData<String>()
    val userEarn : LiveData<String> = _userEarn

    private val _signUpResultMessage = MutableLiveData<String>()
    val signUpResultMessage : LiveData<String> = _signUpResultMessage

    fun checkFinishBtnEnable(stateCount : Int) {
        _saveButtonEnabled.value = if(_currentSignUpLevel.value != 3) stateCount.minus(_currentSignUpLevel.value!!) == 2 else stateCount.minus(_currentSignUpLevel.value!!) == 3
    }

    fun changeLevel() {
        _currentSignUpLevel.value = _currentSignUpLevel.value?.plus(1)
    }

    fun downLevel() {
        _currentSignUpLevel.value = _currentSignUpLevel.value?.minus(1)
    }

    fun setTextByLevel() {
        val level = currentSignUpLevel.value!!
        _signUpTitleText.value = getCurrentTitleText[level.minus(1)]
    }

    fun requestAreaData() {
        viewModelScope.launch {
            userRepository.requestAreaData()
                .onSuccess { _areaData.value = it.toMutableList() }
        }
    }

    fun requestCityData(area : String) {
        viewModelScope.launch {
            userRepository.requestCityData(area)
                .onSuccess { _cityData.value = it.toMutableList() }
        }
    }

    fun setSelectedArea(area : String) {
        _selectedArea.value = area
    }

    fun setSelectedCity(city : String) {
        _selectedCity.value = city
    }

    fun setSelectedGraduation(graduation : String) {
        _selectedGraduation.value = graduation
    }

    fun setSelectedJob(job : MutableList<String>) {
        _selectedJob.value = job
    }

    fun setUserAge(age : String) {
        _userAge.value = age
    }

    fun setUserEarn(earn : String) {
        _userEarn.value = earn.toCharArray().filter { it.checkIsNumber() }.joinToString().replace(", ", "")
    }

    private fun Char.checkIsNumber() : Boolean {
        return this.isDigit()
    }

    fun initSelectedCity() {
        _selectedCity.value = ""
    }

    fun checkFocusLocation(viewTitle : String, isFilled : Boolean) : Int {
        _focusRoute[convertViewPosition(viewTitle)] = isFilled
        return _focusRoute.indexOf(false)
    }

    fun onAllTermsClicked(status : Boolean) {
        for (i in 0 until termsStateList.value!!.size) {
            _termsStateList.value!![i] = termsStateList.value!![i].also { it.selected = status }
        }
        checkTermsFinished()
    }

    fun onTermsClicked() {
        checkTermsFinished()
        checkAllTermsStatus()
    }
    private fun checkAllTermsStatus() {
        _allCheckedState.value =
            termsStateList.value?.count { it.selected } == termsStateList.value?.size
    }

    private fun checkTermsFinished() {
        _termsSaveButtonEnabled.value = termsStateList.value?.count { it.selected && it.required } == 2
    }

    fun requestSignUp() {
        viewModelScope.launch {
            userRepository.requestSignUp(RequestSignUpData(
                sharedPreferenceRepository.getUserId(),
                userAge.value?.toInt() ?: 0,
                selectedArea.value ?: "",
                selectedCity.value ?: "",
                (userEarn.value + "0000").trim().toDouble(),
                selectedGraduation.value ?: "",
                selectedJob.value ?: listOf(),
                termsStateList.value?.count {it.selected } == 3
            ))
                .onSuccess {
                    saveJwt(it.result.accessToken, it.result.refreshToken)
                    _signUpResultMessage.value = "SUCCESS" + it.result.nickname
                }
                .onFailure {
                    val result = Gson().fromJson(it.message!!, ResponseSignInData::class.java)
                    Log.e("----", "requestSignUp: $result", )
                    //_signUpResultMessage.value
                }
        }
    }

    private fun saveJwt(accessToken : String, refreshToken : String) {
        viewModelScope.launch {
            sharedPreferenceRepository.saveAccessToken(accessToken, refreshToken)
        }
    }


    private fun convertViewPosition(content : String) : Int = listOf("AGE", "AREA", "CITY", "EARN", "GRADUATION", "JOB").indexOf(content)
    private val getCurrentLevelText = listOf("기본 정보", "소득 기재", "상세 정보", "약관 동의")
    private val getCurrentTitleText = listOf("나이와 사는 곳을 알려주세요!", "소득을 알려주세요!", "학력과 직업을 입력해주세요", "회원님의 약관동의가 필요해요")
    private fun getTermsData() = mutableListOf(Terms("이용 약관", true, false), Terms("개인정보 처리방침", true, false), Terms("마케팅 정보 수신 동의 (선택)", false, false))
}