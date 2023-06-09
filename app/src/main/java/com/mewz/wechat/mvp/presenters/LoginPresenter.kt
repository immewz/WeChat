package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.mvp.presenters.BasePresenter
import com.mewz.wechat.mvp.views.LoginView

interface LoginPresenter: BasePresenter<LoginView> {
    fun onTapBackButton()
    fun onTapLoginButton()
}