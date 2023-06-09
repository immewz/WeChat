package com.mewz.wechat.activities.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mewz.wechat.activities.BaseActivity
import com.mewz.wechat.activities.MainActivity
import com.mewz.wechat.databinding.ActivityLoginBinding
import com.mewz.wechat.mvp.presenters.LoginPresenter
import com.mewz.wechat.mvp.presenters.impls.LoginPresenterImpl
import com.mewz.wechat.mvp.views.LoginView

class LoginActivity : BaseActivity(), LoginView {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var mPresenter: LoginPresenter

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context,LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpListeners()



    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<LoginPresenterImpl, LoginView>()
    }

    private fun setUpListeners() {
        binding.btnLoginBack.setOnClickListener {
            mPresenter.onTapBackButton()
        }

        binding.btnLogin.setOnClickListener {
            mPresenter.onTapLoginButton()
        }
    }

    override fun navigateToBackScreen() {
        onBackPressed()
    }

    override fun navigateToMainScreen() {
        startActivity(MainActivity.newIntent(this))
    }
}