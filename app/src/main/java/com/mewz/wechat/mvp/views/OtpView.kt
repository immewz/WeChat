package com.mewz.wechat.mvp.views

interface OtpView: BaseView {
    fun navigateToBackScreen()
    fun navigateToRegisterScreen()
    fun showOtp(otp: String)
}