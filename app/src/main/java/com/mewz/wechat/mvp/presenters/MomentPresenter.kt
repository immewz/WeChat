package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.mvp.views.MomentView

interface MomentPresenter: BasePresenter<MomentView> {
    fun onTapNewMomentButton()
}