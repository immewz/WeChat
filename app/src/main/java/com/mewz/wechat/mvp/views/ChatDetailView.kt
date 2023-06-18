package com.mewz.wechat.mvp.views

import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.data.vos.MessageVO
import com.mewz.wechat.data.vos.UserVO

interface ChatDetailView: BaseView {
    fun showUsers(userList: List<UserVO>)
    fun showMessages(messageList: List<MessageVO>)
    fun openGallery()
    fun getImageUrlForFile(file: String)

    fun showGroupMessages(messageList: List<MessageVO>)
    fun getGroups(groupList: List<GroupVO>)
}