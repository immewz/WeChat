package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.mvp.presenters.BasePresenter
import com.mewz.wechat.mvp.views.SplashView

interface SplashPresenter: BasePresenter<SplashView> {
    fun onTapSignUpButton()
    fun onTapLoginButton()
}