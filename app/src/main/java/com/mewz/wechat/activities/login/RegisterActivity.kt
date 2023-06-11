package com.mewz.wechat.activities.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_EMAIL
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.play.core.integrity.p
import com.mewz.wechat.activities.BaseActivity
import com.mewz.wechat.activities.moments.NewMomentActivity.Companion.PICK_IMAGE_REQUEST
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.ActivityRegisterBinding
import com.mewz.wechat.mvp.presenters.RegisterPresenter
import com.mewz.wechat.mvp.presenters.impls.RegisterPresenterImpl
import com.mewz.wechat.mvp.views.RegisterView
import com.mewz.wechat.utils.loadBitMapFromUri
import com.mewz.wechat.utils.months
import com.mewz.wechat.utils.scaleToRatio
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class RegisterActivity : BaseActivity(), RegisterView {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var mPresenter: RegisterPresenter

    private var bitmap: Bitmap? = null
    private var day = ""
    private var month = ""
    private var year = ""
    private var gender = ""

    companion object{
        const val PICK_IMAGE_REQUEST = 1111
        const val EXTRA_EMAIL = "EXTRA_EMAIL"
        const val EXTRA_PHONE = "EXTRA_PHONE"
        fun newIntent(context: Context, email: String, phone: String): Intent {
            val intent = Intent(context,RegisterActivity::class.java)
            intent.putExtra(EXTRA_EMAIL, email)
            intent.putExtra(EXTRA_PHONE, phone)
            return intent
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
            bitmap?.let {
                mPresenter.onTapSignUpButton(getUserInformation(), it)
            }

        }
    }

    private fun getUserInformation(): UserVO {
        val name = binding.etName.text.toString()
        val email = intent?.getStringExtra(EXTRA_EMAIL) ?: ""
        val phone = intent?.getStringExtra(EXTRA_PHONE) ?: ""
        val password = binding.etPassword.text.toString()

        return UserVO(userName = name, email = email, phone = phone, password = password,
        dateOfBirth = "$year-$month-$day", gender = gender)
    }

    private fun setUpDateOfBirth() {
        val dayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getDays())
        val monthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getMonths())
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getYears())

        binding.spinnerDay.adapter = dayAdapter
        binding.spinnerMonth.adapter = monthAdapter
        binding.spinnerYear.adapter = yearAdapter

        setUpDateOfBirthSpinner()
//        Toast.makeText(this, day, Toast.LENGTH_SHORT).show()

    }

    private fun setUpDateOfBirthSpinner() {
        binding.spinnerDay.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0){
                    day = adapter?.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spinnerMonth.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0){
                    month = adapter?.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spinnerYear.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0){
                    year = adapter?.getItemAtPosition(position).toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            val imageUri = data?.data

            imageUri?.let { image ->

                Observable.just(image)
                    .map { it.loadBitMapFromUri(applicationContext) }
                    .map { it.scaleToRatio(0.35) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        bitmap = it
                        binding.ivProfileImage.setImageBitmap(bitmap)
                    }

            }
        }
    }

}