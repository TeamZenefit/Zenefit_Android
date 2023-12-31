package com.zenefit.zenefit_android.presentation.ui.main.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zenefit.zenefit_android.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {
    private lateinit var binding : FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        return binding.root
    }
}