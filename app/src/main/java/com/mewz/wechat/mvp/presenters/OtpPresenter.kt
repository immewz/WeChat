package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.mvp.presenters.BasePresenter
import com.mewz.wechat.mvp.views.OtpView

interface OtpPresenter: BasePresenter<OtpView> {
    fun onTapBackButton()
    fun onTapVerifyButton()
}