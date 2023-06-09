package com.mewz.wechat.activities.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.mewz.wechat.activities.BaseActivity
import com.mewz.wechat.databinding.ActivityRegisterBinding
import com.mewz.wechat.mvp.presenters.RegisterPresenter
import com.mewz.wechat.mvp.presenters.impls.RegisterPresenterImpl
import com.mewz.wechat.mvp.views.RegisterView
import com.mewz.wechat.utils.months
import java.util.*

class RegisterActivity : BaseActivity(), RegisterView {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var mPresenter: RegisterPresenter

    private var day = ""
    private var month = ""
    private var year = ""
    private var gender = ""

    companion object{
        const val PICK_IMAGE_REQUEST = 1111

        fun newIntent(context: Context): Intent {
            return Intent(context,RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpListeners()
        setUpDateOfBirth()
        setUpGender()


    }


    private fun setUpPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
    }

    private fun setUpListeners() {
        binding.btnRegisterBack.setOnClickListener {
            mPresenter.onTapBackButton()
        }

        binding.btnProfileImage.setOnClickListener {
            mPresenter.onTapProfileImage()
        }

        binding.btnSignUp.setOnClickListener {
            mPresenter.onTapSignUpButton()
        }
    }

    private fun setUpDateOfBirth() {
        val dayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getDays())
        val monthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getMonths())
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getYears())

        binding.spinnerDay.adapter = dayAdapter
        binding.spinnerMonth.adapter = monthAdapter
        binding.spinnerYear.adapter = yearAdapter



    }

    private fun getYears(): List<String> {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val years = mutableListOf<String>("Year")
        for (year in 1900..currentYear) {
            years.add(year.toString())
        }
        return years
    }

    private fun getMonths(): List<String> {
        return months
    }

    private fun getDays(): List<String> {
        val days = mutableListOf<String>("Day")
        for (i in 1..31) {
            days.add(i.toString())
        }
        return days
    }

    private fun setUpGender() {
        binding.rbMale.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                gender = "Male"
            }
        }

        binding.rbFemale.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                gender = "Female"
            }
        }

        binding.rbOther.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                gender = "Other"
            }
        }
    }

    override fun navigateToBackScreen() {
        onBackPressed()
    }

    override fun navigateToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

    override fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Selected Picture"), PICK_IMAGE_REQUEST)
    }
}