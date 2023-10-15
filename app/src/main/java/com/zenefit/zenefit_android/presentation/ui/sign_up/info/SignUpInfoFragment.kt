package com.zenefit.zenefit_android.presentation.ui.sign_up.info

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowId.FocusObserver
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zenefit.zenefit_android.R
import com.zenefit.zenefit_android.databinding.FragmentSignUpInfoBinding
import com.zenefit.zenefit_android.presentation.component.ComponentBottomMultipleChoice
import com.zenefit.zenefit_android.presentation.component.ComponentBottomOneChoice
import com.zenefit.zenefit_android.presentation.ui.sign_up.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpInfoFragment : Fragment() {
    private lateinit var binding: FragmentSignUpInfoBinding
    private val viewModel: SignUpViewModel by activityViewModels()

    private lateinit var imm : InputMethodManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_info, container, false)

        initBinding()
        initComponents()
        initInputMethodManager()
        initFocus()
        initObserve()

        return binding.root
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initComponents() {
        binding.fgSignUpInfoComponentAge.apply {
            initComponent("AGE")
            setTextWatcher(::observeAge)
        }
        binding.fgSignUpInfoComponentCity.initComponent("AREA", ::onClickItems)
        binding.fgSignUpInfoComponentAddress.initComponent("CITY", ::onClickItems)

        binding.fgSignUpInfoComponentEarn.apply {
            initComponent("EARN")
            setTextWatcher(::observeEarn)
        }
        binding.fgSignUpInfoComponentGraduation.initComponent("GRADUATION", ::onClickItems)
        binding.fgSignUpInfoComponentJob.initComponent("JOB", ::onClickItems)
    }

    private fun initInputMethodManager() {
        imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private fun initFocus() {
        /** 실행 초기 Age에 Focus 부여 **/
        binding.fgSignUpInfoComponentAge.setFocus()

        binding.root.viewTreeObserver.addOnGlobalFocusChangeListener { view, view2 ->
            if(view2.javaClass.toString().contains("Edit")) showKeyBoard(view2)  else hideKeyBoard(view2)
        }
    }

    private fun showKeyBoard(view : View) {
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideKeyBoard(view : View) {
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun initObserve() {
        observeAreaData()
        observeCityData()

        observeSelectedArea()
        observeSelectedCity()

        observeCurrentLevel()

        observeGraduation()
        observeJob()
    }

    private fun observeAreaData() {
        viewModel.areaData.observe(viewLifecycleOwner) {
            it.openOneChoiceBottomSheetDialog("AREA")
        }
    }

    private fun observeCityData() {
        viewModel.cityData.observe(viewLifecycleOwner) {
            it.openOneChoiceBottomSheetDialog("CITY")
        }
    }

    private fun observeSelectedArea() {
        viewModel.selectedArea.observe(viewLifecycleOwner) {
            binding.fgSignUpInfoComponentCity.setSelectedText(it)

            checkLevelPass()

            viewModel.initSelectedCity()
        }
    }

    private fun observeSelectedCity() {
        viewModel.selectedCity.observe(viewLifecycleOwner) {
            binding.fgSignUpInfoComponentAddress.setSelectedText(it)
            checkLevelPass()
        }
    }

    private fun observeAge(age : String) {
        viewModel.setUserAge(age)
        checkLevelPass()
    }

    private fun observeEarn(earn : String) {
        viewModel.setUserEarn(earn)
        checkLevelPass()
    }

    private fun observeGraduation() {
        viewModel.selectedGraduation.observe(viewLifecycleOwner) {
            binding.fgSignUpInfoComponentGraduation.setSelectedText(it)
            checkLevelPass()
        }
    }

    private fun observeJob() {
        viewModel.selectedJob.observe(viewLifecycleOwner) {
            if (it.size == 1) binding.fgSignUpInfoComponentJob.setSelectedText(it[0])
            else binding.fgSignUpInfoComponentJob.setSelectedText("${it[0]} 외 ${it.size - 1}개")

            checkLevelPass()
        }
    }

    private fun observeCurrentLevel() {
        viewModel.currentSignUpLevel.observe(viewLifecycleOwner) {
            viewModel.setTextByLevel()
        }
    }

    private fun onClickItems(type: String) {
        when (type) {
            "AREA" -> viewModel.requestAreaData()
            "CITY" -> if (!viewModel.selectedArea.value.isNullOrEmpty()) viewModel.requestCityData(
                viewModel.selectedArea.value!!
            )

            "GRADUATION" -> returnGraduationList().openOneChoiceBottomSheetDialog(type)
            "JOB" -> returnJobList().openMultiChoiceBottomSheetDialog(type)
        }
    }

    private fun List<String>.openOneChoiceBottomSheetDialog(type: String) {
        if (type == "AREA") ComponentBottomOneChoice(this, viewModel.selectedArea.value).show(
            childFragmentManager,
            type
        )
        else if (type == "CITY") ComponentBottomOneChoice(this, viewModel.selectedCity.value).show(
            childFragmentManager,
            type
        )
        else if (type == "GRADUATION") ComponentBottomOneChoice(
            this,
            viewModel.selectedGraduation.value
        ).show(childFragmentManager, type)
    }

    private fun List<String>.openMultiChoiceBottomSheetDialog(type: String) {
        if (type == "JOB") ComponentBottomMultipleChoice(this, viewModel.selectedJob.value).show(
            childFragmentManager,
            type
        )
    }

    private fun checkLevelPass() {
        viewModel.checkFinishBtnEnable(
            listOf(
                binding.fgSignUpInfoComponentAge.returnFinishState(),
                !viewModel.selectedArea.value.isNullOrEmpty(),
                !viewModel.selectedCity.value.isNullOrEmpty(),
                binding.fgSignUpInfoComponentEarn.returnFinishState(),
                !viewModel.selectedGraduation.value.isNullOrEmpty(),
                !viewModel.selectedJob.value.isNullOrEmpty()
            ).count { it })
    }

    /** Dummy **/
    private fun returnGraduationList(): List<String> = listOf(
        "고졸 미만", "고교 재학", "고졸 예정", "고교 졸업", "대학 재학", "대졸 예정", "대학 졸업", "석박사"
    )

    private fun returnJobList(): List<String> = listOf(
        "재직자", "자영업자", "미취업자", "프리랜서", "일용 근로자", "(예비) 창업자", "단기근로자", "영농종사자"
    )
}