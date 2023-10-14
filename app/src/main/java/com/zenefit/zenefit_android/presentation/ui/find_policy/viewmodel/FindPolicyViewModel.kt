package com.zenefit.zenefit_android.presentation.ui.find_policy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenefit.zenefit_android.data.remote.response.ResponsePolicyCountData
import com.zenefit.zenefit_android.domain.repository.PolicyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindPolicyViewModel @Inject constructor(private val policyRepository: PolicyRepository): ViewModel() {
    private val _findPolicyResult = MutableLiveData<ResponsePolicyCountData.ResultPolicyCountData>()
    val findPolicyResult : LiveData<ResponsePolicyCountData.ResultPolicyCountData> = _findPolicyResult

    init {
        requestPolicyCount()
    }

    private fun requestPolicyCount() {
        viewModelScope.launch {
            policyRepository.requestPolicyCount()
                .onSuccess { _findPolicyResult.value = it }
        }
    }
}