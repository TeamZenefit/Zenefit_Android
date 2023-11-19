package com.zenefit.zenefit_android.presentation.ui.interest_benefit_policy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.zenefit.zenefit_android.data.remote.response.ResponseUserPolicyData
import com.zenefit.zenefit_android.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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

    private val _deleteResponseStatus = MutableLiveData<Boolean>()
    val deleteResponseStatus : LiveData<Boolean> = _deleteResponseStatus

    fun setActivityType(type : String) {
        _type.value = type
    }

    fun changeWriteStatus() {
        _writeStatus.value = writeStatus.value?.not()
    }

    fun requestInterestPolicyList() : Flow<PagingData<ResponseUserPolicyData.ResultUserPolicyData>> {
        return userRepository.requestInterestPolicy()
    }

    fun requestInterestPolicyCount() {
        viewModelScope.launch {
            userRepository.requestInterestPolicyCount()
                .onSuccess { _policyCount.value = it.size }
                .onFailure { it.printStackTrace() }
        }
    }

    fun requestBenefitPolicyList() : Flow<PagingData<ResponseUserPolicyData.ResultUserPolicyData>> {
        return userRepository.requestBenefitPolicy()
    }

    fun requestBenefitPolicyCount() {
        viewModelScope.launch {
            userRepository.requestBenefitPolicyCount()
                .onSuccess { _policyCount.value = it.size }
                .onFailure { it.printStackTrace() }
        }
    }

    fun requestRemoveInterestPolicy(policyId : Int) {
        viewModelScope.launch {
            userRepository.requestDeleteInterestPolicy(policyId)
                .onSuccess { _deleteResponseStatus.value = it.isSuccess }
                .onFailure { it.printStackTrace() }
        }
    }

    fun requestRemoveBenefitPolicy(policyId : Int) {
        viewModelScope.launch {
            userRepository.requestDeleteBenefitPolicy(policyId)
                .onSuccess { _deleteResponseStatus.value = it.isSuccess }
                .onFailure { it.printStackTrace() }
        }
    }
}