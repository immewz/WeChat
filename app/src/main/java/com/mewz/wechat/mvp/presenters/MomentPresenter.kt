package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.data.vos.MomentVO
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.delegtes.MomentItemViewHolderDelegate
import com.mewz.wechat.mvp.views.MomentView

interface MomentPresenter: BasePresenter<MomentView>, MomentItemViewHolderDelegate {
    fun onTapNewMomentButton()
    fun createMoment(moment: MyMomentVO)

    fun getUserId(): String
}