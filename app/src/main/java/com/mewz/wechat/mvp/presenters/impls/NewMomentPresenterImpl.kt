package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.NewMomentPresenter
import com.mewz.wechat.mvp.views.NewMomentView

class NewMomentPresenterImpl: NewMomentPresenter, AbstractBasePresenter<NewMomentView>() {

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun onTapBackButton() {
        mView.navigateToBackScreen()
    }

    override fun onTapCreateButton() {
        mView.createNewMoment()
    }

    override fun onTapAddImage() {
        mView.openGallery()
    }


}