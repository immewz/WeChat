package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.delegtes.AlphabetActionIItemDelegate
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.mvp.views.NewGroupView

interface NewGroupPresenter: BasePresenter<NewGroupView>, AlphabetActionIItemDelegate, ChatItemViewHolderDelegate {

    fun getContacts(scannerId:String)
    fun getUserId() :String
    fun onTapCreateButton(timeStamp: Long, groupName: String, userList:List<String>)
}