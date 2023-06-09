package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.delegtes.GroupItemViewHolderDelegate
import com.mewz.wechat.mvp.views.ContactView

interface ContactPresenter: BasePresenter<ContactView>, GroupItemViewHolderDelegate {
    fun onTapAddNewContactButton()
    fun onTapAddNewGroupButton()
}