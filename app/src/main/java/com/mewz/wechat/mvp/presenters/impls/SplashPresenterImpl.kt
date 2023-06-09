package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.SplashPresenter
import com.mewz.wechat.mvp.views.SplashView

class SplashPresenterImpl: SplashPresenter, AbstractBasePresenter<SplashView>() {

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun onTapSignUpButton() {
        mView.navigateToOtpScreen()
    }

    override fun onTapLoginButton() {
        mView.navigateToLoginScreen()
    }


}