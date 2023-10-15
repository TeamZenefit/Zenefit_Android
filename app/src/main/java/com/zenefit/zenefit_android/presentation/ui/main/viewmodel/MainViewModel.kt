package com.zenefit.zenefit_android.presentation.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.zenefit.zenefit_android.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {


}