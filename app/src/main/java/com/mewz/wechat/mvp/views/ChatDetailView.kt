package com.mewz.wechat.mvp.views

import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.data.vos.MessageVO
import com.mewz.wechat.data.vos.PrivateMessageVO
import com.mewz.wechat.data.vos.UserVO

interface ChatDetailView: BaseView {
    fun showUsers(userList: List<UserVO>)
    fun showMessages(messageList: List<PrivateMessageVO>)
    fun showGallery()
    fun getImageUrlForFile(file: String)

    fun showGroupMessages(messageList: List<PrivateMessageVO>)
    fun getGroups(groupList: List<GroupVO>)
}