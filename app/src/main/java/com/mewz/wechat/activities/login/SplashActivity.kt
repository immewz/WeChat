package com.mewz.wechat.activities.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.mewz.wechat.activities.BaseActivity
import com.mewz.wechat.databinding.ActivitySplashBinding
import com.mewz.wechat.mvp.presenters.SplashPresenter
import com.mewz.wechat.mvp.presenters.impls.SplashPresenterImpl
import com.mewz.wechat.mvp.views.SplashView

class SplashActivity : BaseActivity(), SplashView {

    private lateinit var binding: ActivitySplashBinding

    private lateinit var mPresenter: SplashPresenter

    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context,SplashActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpListeners()


    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<SplashPresenterImpl, SplashView>()
    }

    private fun setUpListeners() {
        binding.btnLogin.setOnClickListener {
            mPresenter.onTapLoginButton()
        }

        binding.btnSignUp.setOnClickListener {
            mPresenter.onTapSignUpButton()
        }
    }

    override fun navigateToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
    }

    override fun navigateToOtpScreen() {
        startActivity(OTPActivity.newIntent(this))
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_LONG).show()
    }
}