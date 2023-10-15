package com.zenefit.zenefit_android.presentation.ui.manual

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.data.data.Manual
import com.zenefit.zenefit_android.databinding.ActivityManualBinding
import com.zenefit.zenefit_android.presentation.ui.manual.adapter.ManualAdapter

class ManualActivity : AppCompatActivity() {
    private lateinit var binding : ActivityManualBinding
    private val manualAdapter : ManualAdapter by lazy {
        ManualAdapter(returnManualList())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manual)

        initBinding()
        initUi()
    }

    private fun initBinding() {
        binding.activity = this
    }

    private fun initUi() {
        initPager()
    }

    private fun initPager() {
        binding.manualPagerContent.adapter = manualAdapter
        TabLayoutMediator(binding.manualTabIndicator, binding.manualPagerContent) { tab, _ ->
        }.attach()
    }

    fun finishActivity() {
        finish()
    }

    /** Dummy **/
    private fun returnManualList() : List<Manual> = listOf(
        Manual(R.drawable.img_manual_0, resources.getString(R.string.manual_first_title), ""),
        Manual(R.drawable.img_manual_1, resources.getString(R.string.manual_second_title), resources.getString(R.string.manual_second_sub_title)),
        Manual(R.drawable.img_manual_2, resources.getString(R.string.manual_third_title), resources.getString(R.string.manual_third_sub_title)),
        Manual(R.drawable.img_manual_3, resources.getString(R.string.manual_fourth_title), resources.getString(R.string.manual_fourth_sub_title)),
        Manual(R.drawable.img_manual_4, resources.getString(R.string.manual_fifth_title), resources.getString(R.string.manual_fifth_sub_title))
    )
}