package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.delegtes.GroupItemViewHolderDelegate
import com.mewz.wechat.mvp.views.ChatView

interface ChatPresenter: BasePresenter<ChatView>, ChatItemViewHolderDelegate, GroupItemViewHolderDelegate {
    fun getContacts(scannerId:String)

    fun getUserId() : String

    fun getChatHistoryUserId(
        senderId: String
    )

    fun getGroupMessages(groupId: Long, onSuccess: (Int) -> Unit)
}