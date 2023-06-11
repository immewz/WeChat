package com.mewz.wechat.activities.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mewz.wechat.activities.BaseActivity
import com.mewz.wechat.databinding.ActivityOtpBinding
import com.mewz.wechat.mvp.presenters.OtpPresenter
import com.mewz.wechat.mvp.presenters.impls.OtpPresenterImpl
import com.mewz.wechat.mvp.views.OtpView

class OTPActivity : BaseActivity(), OtpView {

    private lateinit var binding: ActivityOtpBinding

    private lateinit var mPresenter: OtpPresenter

    private var mOTPCode = ""
    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context,OTPActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpListeners()

        mPresenter.onUiReady(this, this)

    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<OtpPresenterImpl, OtpView>()
    }

    private fun setUpListeners() {
        binding.btnOTPBack.setOnClickListener {
            mPresenter.onTapBackButton()
        }

        binding.btnVerify.setOnClickListener {
            mPresenter.onTapVerifyButton()
        }
    }

    override fun navigateToBackScreen() {
        onBackPressed()
    }

    override fun navigateToRegisterScreen() {
        val email = binding.etGmail.text.toString()
        val phone = binding.etPhone.text.toString()

        if(binding.otpView.otp.toString() == mOTPCode) {
            startActivity(RegisterActivity.newIntent(this, email, phone))
            finish()
        }
    }

    override fun showOtp(otp: String) {
        mOTPCode = otp
        Toast.makeText(this, otp, Toast.LENGTH_SHORT).show()
        binding.otpView.setOTP(otp)
    }
}