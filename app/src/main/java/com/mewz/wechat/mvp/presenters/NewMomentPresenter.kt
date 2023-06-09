package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.delegtes.NewMomentImageViewHolderDelegate
import com.mewz.wechat.mvp.views.NewMomentView

interface NewMomentPresenter: BasePresenter<NewMomentView>, NewMomentImageViewHolderDelegate {
    fun onTapBackButton()
    fun onTapCreateButton()
}