package com.zenefit.zenefit_android.presentation.ui.interest_benefit_policy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenefit.zenefit_android.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterestBenefitPolicyViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    private val _type = MutableLiveData<String>()
    val type : LiveData<String> = _type

    private val _writeStatus = MutableLiveData<Boolean>(false)
    val writeStatus : LiveData<Boolean> = _writeStatus

    private val _policyCount = MutableLiveData<Int>()
    val policyCount : LiveData<Int> = _policyCount

    fun setActivityType(type : String) {
        _type.value = type

        if(type.contains("Interest")) requestInterestPolicyCount() else requestBenefitPolicyCount()
    }

    fun changeWriteStatus() {
        _writeStatus.value = writeStatus.value?.not()
    }

    private fun requestInterestPolicyCount() {
        viewModelScope.launch {
            userRepository.requestInterestPolicyCount()
                .onSuccess { _policyCount.value = it.size
                    Log.e("----", "requestInterestPolicyCount: $it", )}
                .onFailure { Log.e("----", "requestInterestPolicyCount: ${it.message}", ) }
        }
    }

    private fun requestBenefitPolicyCount() {
        viewModelScope.launch {
            userRepository.requestBenefitPolicyCount()
                .onSuccess { _policyCount.value = it.size }
        }
    }
}