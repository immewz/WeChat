package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.RegisterPresenter
import com.mewz.wechat.mvp.views.RegisterView

class RegisterPresenterImpl: RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun onTapBackButton() {
        mView.navigateToBackScreen()
    }

    override fun onTapProfileImage() {
        mView.openGallery()
    }

    override fun onTapSignUpButton() {
        mView.navigateToLoginScreen()
    }

    override fun onPhotoTaken(bitmap: Bitmap) {

    }


}