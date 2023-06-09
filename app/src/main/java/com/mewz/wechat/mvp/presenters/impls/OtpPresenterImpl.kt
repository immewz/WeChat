package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.OtpPresenter
import com.mewz.wechat.mvp.views.OtpView

class OtpPresenterImpl: OtpPresenter, AbstractBasePresenter<OtpView>() {

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun onTapBackButton() {
        mView.navigateToBackScreen()
    }

    override fun onTapVerifyButton() {
        mView.navigateToRegisterScreen()
    }
}