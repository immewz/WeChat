package com.mewz.wechat.mvp.presenters

import android.graphics.Bitmap
import com.mewz.wechat.data.vos.MessageVO
import com.mewz.wechat.data.vos.PrivateMessageVO
import com.mewz.wechat.mvp.views.ChatDetailView

interface ChatDetailPresenter: BasePresenter<ChatDetailView> {

    fun onTapGetImageButton()

    fun getUserId(): String
    fun sendMessage(senderId: String, receiverId: String, timeStamp: Long, message: PrivateMessageVO)

    fun getMessages(
        senderId: String,
        receiverId: String
    )

    fun uploadAndSendImage(bitmap: Bitmap)

    fun sendGroupMessage(groupId: Long, timeStamp:Long, message:PrivateMessageVO)

    fun getGroupMessages(
        groupId:Long
    )
}