package com.zenefit.zenefit_android.presentation.ui.find_policy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.ActivityFindPolicyBinding

class FindPolicyActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFindPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_policy)


    }


}