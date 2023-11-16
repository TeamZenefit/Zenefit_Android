package com.zenefit.zenefit_android.presentation.ui.policy_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zenefit.zenefit_android.data.data.DummyPolicyDetail

class PolicyDetailViewModel : ViewModel() {

    private val _type = MutableLiveData<String>() // CAN / CANT
    val type : LiveData<String> = _type

    fun setType(type : String) {
        _type.value = type
    }


    /** Dummy **/
    fun policyDetailData() : DummyPolicyDetail =
        DummyPolicyDetail("춘배 창업지원정책","신청 기간이 아니에요",100, "유치원에 다니는 만 3~5세 아동에게 유아학비, 방과후 과정비 등 지원", "주민등록등본, 신분증, 확정일 자부 월세 계약서 사본, 계약금 지급 영수증", "홈페이지에 접속해서 별도 회원가입한 후 수강신청", "상시신청")
}