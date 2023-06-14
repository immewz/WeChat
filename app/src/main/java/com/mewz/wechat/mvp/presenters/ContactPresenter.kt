package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.delegtes.AlphabetActionIItemDelegate
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.delegtes.GroupItemViewHolderDelegate
import com.mewz.wechat.mvp.views.ContactView

interface ContactPresenter: BasePresenter<ContactView>, GroupItemViewHolderDelegate, AlphabetActionIItemDelegate, ChatItemViewHolderDelegate {
    fun onTapAddNewContactButton()
    fun onTapAddNewGroupButton()

    fun getContacts(scannerId: String)
    fun getUserId() :String
}