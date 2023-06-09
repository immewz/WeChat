package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.ProfilePresenter
import com.mewz.wechat.mvp.views.ProfileView

class ProfilePresenterImpl: ProfilePresenter, AbstractBasePresenter<ProfileView>() {

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun onTapEditProfileButton() {
        mView.showEditProfileDialog()
    }

    override fun onTapChangeImage() {
        mView.openGallery()
    }

    override fun onTapQrCodeImage() {
        mView.showQrCode()
    }


}