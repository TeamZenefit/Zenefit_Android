package com.zenefit.zenefit_android.presentation.ui.main.policy_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PolicyListViewModel : ViewModel() {

    var searchText = MutableLiveData<String>().apply { value = "" }

    private val _isFilterDrawerOpened = MutableLiveData<Boolean>(false)
    val isFilterDrawerOpened : LiveData<Boolean> = _isFilterDrawerOpened

    private val _currentSelectedFilter = MutableLiveData<String>("benefit")
    val currentSelectedFilter : LiveData<String> = _currentSelectedFilter

    fun changeFilterDrawerState() {
        _isFilterDrawerOpened.value = _isFilterDrawerOpened.value?.not()
    }

    fun setCurrentSelectedFilter(filter : String) {
        _currentSelectedFilter.value = filter
    }

}