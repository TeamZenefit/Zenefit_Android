package com.zenefit.zenefit_android.data.remote.reqeust

data class RequestSignUpData(
    val userId : String,
    val age : Int,
    val areaCode : String,
    val cityCode : String,
    val lastYearIncome : Int,
    val educationType : String,
    val jobs : List<String>,
    val marketingStatus : Boolean
)
