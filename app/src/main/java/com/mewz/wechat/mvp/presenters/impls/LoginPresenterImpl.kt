package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.LoginPresenter
import com.mewz.wechat.mvp.views.LoginView

class LoginPresenterImpl: LoginPresenter, AbstractBasePresenter<LoginView>() {


    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun onTapBackButton() {
        mView.navigateToBackScreen()
    }

    override fun onTapLoginButton() {
        mView.navigateToMainScreen()
    }


}