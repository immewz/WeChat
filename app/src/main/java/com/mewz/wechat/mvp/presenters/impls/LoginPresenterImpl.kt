package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.AuthModel
import com.mewz.wechat.data.models.AuthModelImpl
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.LoginPresenter
import com.mewz.wechat.mvp.views.LoginView

class LoginPresenterImpl: LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthModel: AuthModel = AuthModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun onTapBackButton() {
        mView.navigateToBackScreen()
    }

    override fun onTapLoginButton(phone: String, email: String, password: String) {
        mAuthModel.login(
            phone, email, password,
            onSuccess = { mView.navigateToMainScreen() },
            onFailure = { mView.showError(it) }
        )

    }


}