package com.zenefit.zenefit_android.presentation.ui.sign_up

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ActivitySignUpBinding
import com.zenefit.zenefit_android.presentation.ui.sign_up.info.SignUpInfoFragment
import com.zenefit.zenefit_android.presentation.ui.sign_up.terms.SignUpTermsFragment
import com.zenefit.zenefit_android.presentation.ui.sign_up.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    private val viewModel : SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        initBinding()
        initUi()
        initObserve()
    }

    private fun initBinding() {
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initUi() {
        initFragment()
    }

    private fun initObserve() {
        viewModel.currentSignUpLevel.observe(this) {
            if(it == 4) supportFragmentManager.beginTransaction().addToBackStack(null).add(R.id.sign_up_layout_container, SignUpTermsFragment()).commit()
        }
    }

    fun onBackPress() {
        if(supportFragmentManager.backStackEntryCount == 0) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction().commit()
            viewModel.downLevel()
        }
    }

    private fun initFragment() {
        SignUpInfoFragment().changeFragments()
    }

    private fun Fragment.changeFragments() {
        supportFragmentManager.beginTransaction().replace(R.id.sign_up_layout_container, this).commit()
    }

}