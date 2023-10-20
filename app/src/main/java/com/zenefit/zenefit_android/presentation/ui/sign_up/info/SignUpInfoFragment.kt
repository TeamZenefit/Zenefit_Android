package com.zenefit.zenefit_android.presentation.ui.sign_up.info

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener
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

    private val observeListener by lazy {
        OnGlobalFocusChangeListener { p0, p1 -> p1?.let { if(p1.javaClass.toString().contains("Edit")) showKeyBoard(p1)  else hideKeyBoard(p1) } }
    }
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
        binding.fragment = this
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

        binding.fgSignUpInfoComponentEarn.apply { initComponent("EARN") }

        binding.fgSignUpInfoComponentGraduation.initComponent("GRADUATION", ::onClickItems)
        binding.fgSignUpInfoComponentJob.initComponent("JOB", ::onClickItems)
    }

    private fun initInputMethodManager() {
        imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onResume() {
        super.onResume()
        Log.e("----", "onResume: ", )
    }

    private fun initFocus() {
        /** 실행 초기 Age에 Focus 부여 **/
        binding.fgSignUpInfoComponentAge.setFocus()

        binding.root.viewTreeObserver.addOnGlobalFocusChangeListener(observeListener)
    }

    private fun showKeyBoard(view : View) {
        imm.showSoftInput(view, 0)
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

            viewModel.checkFocusLocation("AREA", !it.equals("시/도")).requestFocus()
        }
    }

    private fun observeSelectedCity() {
        viewModel.selectedCity.observe(viewLifecycleOwner) {
            binding.fgSignUpInfoComponentAddress.setSelectedText(it)
            checkLevelPass()

            viewModel.checkFocusLocation("CITY", !it.equals("시/군/구") && !it.isNullOrEmpty()).requestFocus()
        }
    }

    private fun observeAge(age : String) {
        viewModel.setUserAge(age)
        checkLevelPass()

        viewModel.checkFocusLocation("AGE", age.length == 2).requestFocus()
    }

    private fun observeEarn(earn : String) {
        viewModel.setUserEarn(earn)
        checkLevelPass()

        if(viewModel.currentSignUpLevel.value!! > 2) viewModel.checkFocusLocation("EARN", earn.isNotEmpty()).requestFocus()
    }

    private fun observeGraduation() {
        viewModel.selectedGraduation.observe(viewLifecycleOwner) {
            binding.fgSignUpInfoComponentGraduation.setSelectedText(it)
            checkLevelPass()

            viewModel.checkFocusLocation("GRADUATION", !it.equals("학력")).requestFocus()
        }
    }

    private fun observeJob() {
        viewModel.selectedJob.observe(viewLifecycleOwner) {
            if (it.size == 1) binding.fgSignUpInfoComponentJob.setSelectedText(it[0])
            else binding.fgSignUpInfoComponentJob.setSelectedText("${it[0]} 외 ${it.size - 1}개")

            checkLevelPass()

            viewModel.checkFocusLocation("JOB", !it.equals("직업")).requestFocus()
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

    private fun Int.requestFocus() {
        when(this) {
            0 -> binding.fgSignUpInfoComponentAge.setFocus()
            1 -> binding.fgSignUpInfoComponentCity.requestFocus()
            2 -> binding.fgSignUpInfoComponentAddress.requestFocus()
            3 -> binding.fgSignUpInfoComponentEarn.requestFocus()
            4 -> binding.fgSignUpInfoComponentGraduation.requestFocus()
            5 -> binding.fgSignUpInfoComponentJob.requestFocus()
        }
    }

    fun onSaveClick() {
        binding.fgSignUpInfoBtnSave.requestFocus()
        viewModel.changeLevel()
        when(viewModel.currentSignUpLevel.value) {
            2 -> {
                binding.fgSignUpInfoComponentEarn.setTextWatcher(::observeEarn)
                viewModel.checkFocusLocation("EARN", !viewModel.userEarn.value.isNullOrEmpty()).requestFocus()
            }
            3 -> binding.fgSignUpInfoComponentGraduation.requestFocus()
        }
    }

    override fun onStop() {
        super.onStop()
        binding.root.viewTreeObserver.removeOnGlobalFocusChangeListener(observeListener)
    }

    /** Dummy **/
    private fun returnGraduationList(): List<String> = listOf(
        "고졸 미만", "고교 재학", "고졸 예정", "고교 졸업", "대학 재학", "대졸 예정", "대학 졸업", "석박사"
    )

    private fun returnJobList(): List<String> = listOf(
        "재직자", "자영업자", "미취업자", "프리랜서", "일용 근로자", "(예비) 창업자", "단기근로자", "영농종사자"
    )
}