package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.ChatModel
import com.mewz.wechat.data.models.ChatModelImpl
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.OtpPresenter
import com.mewz.wechat.mvp.views.OtpView

class OtpPresenterImpl: OtpPresenter, AbstractBasePresenter<OtpView>() {

    private var mChatModel: ChatModel = ChatModelImpl
    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mChatModel.getOtp(
            onSuccess = { mView.showOtp(it) },
            onFailure = { mView.showError(it) }
        )
    }

    override fun onTapBackButton() {
        mView.navigateToBackScreen()
    }

    override fun onTapVerifyButton() {
        mView.navigateToRegisterScreen()
    }
}