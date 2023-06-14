package com.mewz.wechat.mvp.views

import com.mewz.wechat.data.vos.UserVO

interface NewGroupView: BaseView {

    fun navigateToChatDetailScreen(userId: String)
    fun showContacts(userList: List<UserVO>)

    fun addUserToGroup(userId: String, isChecked: Boolean)

}