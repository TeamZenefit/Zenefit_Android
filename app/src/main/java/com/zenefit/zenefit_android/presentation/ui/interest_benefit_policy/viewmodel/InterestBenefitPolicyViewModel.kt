package com.zenefit.zenefit_android.presentation.ui.interest_benefit_policy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InterestBenefitPolicyViewModel : ViewModel() {
    private val _type = MutableLiveData<String>()
    val type : LiveData<String> = _type

    private val _writeStatus = MutableLiveData<Boolean>(false)
    val writeStatus : LiveData<Boolean> = _writeStatus

    fun setActivityType(type : String) {
        _type.value = type
    }

    fun changeWriteStatus() {
        _writeStatus.value = writeStatus.value?.not()
    }
}