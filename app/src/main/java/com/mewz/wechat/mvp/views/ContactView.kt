package com.mewz.wechat.mvp.views

import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.data.vos.UserVO

interface ContactView: BaseView {
    fun navigateToNewContactScreen()
    fun navigateToNewGroupScreen()
    fun navigateToChatDetailScreen(userId: String)

    fun showContacts(contactList: List<UserVO>)
    fun addUserToGroup(userId: String)
    fun getGroupList(groupList: List<GroupVO>)
}