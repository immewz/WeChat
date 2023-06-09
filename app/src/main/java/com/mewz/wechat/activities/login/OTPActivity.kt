package com.mewz.wechat.activities.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mewz.wechat.activities.BaseActivity
import com.mewz.wechat.databinding.ActivityOtpBinding
import com.mewz.wechat.mvp.presenters.OtpPresenter
import com.mewz.wechat.mvp.presenters.impls.OtpPresenterImpl
import com.mewz.wechat.mvp.views.OtpView

class OTPActivity : BaseActivity(), OtpView {

    private lateinit var binding: ActivityOtpBinding

    private lateinit var mPresenter: OtpPresenter

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
        startActivity(RegisterActivity.newIntent(this))
        finish()
    }
}