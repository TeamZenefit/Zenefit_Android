package com.zenefit.zenefit_android.presentation.ui.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenefit.zenefit_android.data.remote.response.ResponseHomeData
import com.zenefit.zenefit_android.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private val _homeData = MutableLiveData<ResponseHomeData.ResultHomeData>()
    val homeData : LiveData<ResponseHomeData.ResultHomeData> = _homeData

    fun requestHomeData() {
        viewModelScope.launch {
            userRepository.requestHomeData()
                .onSuccess { _homeData.value = it }
                .onFailure { Log.e("----", "requestHomeData: FAIL $it", ) }
        }
    }
}