package com.zenefit.zenefit_android.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ActivityLoginBinding
import com.zenefit.zenefit_android.presentation.ui.find_policy.FindPolicyActivity
import com.zenefit.zenefit_android.presentation.ui.login.viewmodel.LoginViewModel
import com.zenefit.zenefit_android.presentation.ui.sign_up.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private val viewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initBinding()
        initObserve()
    }

    private fun initBinding() {
        binding.activity = this
        binding.lifecycleOwner = this
    }

    private fun initObserve() {
        observeLoginCode()
    }

    fun onSocialAuthClicked() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            signInWithKakaoTalk()
        } else {
            signInWithKakaoAccount()
        }
    }

    private fun signInWithKakaoTalk() {
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
            if (error != null) {
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    finish()
                    return@loginWithKakaoTalk
                }
                signInWithKakaoAccount()
            } else if (token != null) {
                viewModel.requestSignIn(token.accessToken)
            }
        }
    }

    private fun signInWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this, callback = kakaoAccountSignInCallback)
    }

    private fun observeLoginCode() {
        viewModel.loginCode.observe(this) {
            when(it) {
                2001, 2005 -> {
                    startActivity(Intent(this, SignUpActivity::class.java))
                }
                else -> {
                    startActivity(Intent(this, FindPolicyActivity::class.java))
                }
            }
        }
    }

    private val kakaoAccountSignInCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            finish()
        } else if (token != null) {
            viewModel.requestSignIn(token.accessToken)
        }
    }
}