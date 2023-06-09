package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.MomentPresenter
import com.mewz.wechat.mvp.views.MomentView

class MomentPresenterImpl: MomentPresenter, AbstractBasePresenter<MomentView>() {

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun onTapNewMomentButton() {
        mView.navigateToNewMomentScreen()
    }
}