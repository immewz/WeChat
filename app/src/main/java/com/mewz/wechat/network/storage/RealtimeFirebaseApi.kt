package com.mewz.wechat.network.storage

import android.graphics.Bitmap
import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.data.vos.MessageVO

interface RealtimeFirebaseApi {

    fun getOtp(
        onSuccess: (code: String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun sendMessage(senderId: String, receiverId: String, timeStamp: Long, message: MessageVO)
    fun getMessages(
        senderId: String,
        receiverId: String,
        onSuccess: (messageList: List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun uploadAndSendImage(
        bitmap: Bitmap,
        onSuccess: (file: String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getChatHistoryUserId(
        senderId: String,
        onSuccess: (messageList: List<String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun addGroup(timeStamp: Long, groupName: String, userList: List<String>)

    fun getGroups(
        onSuccess: (groupIdList: List<GroupVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun sendGroupMessage(groupId: Long,timeStamp:Long, message:MessageVO)

    fun getGroupMessages(
        groupId:Long,
        onSuccess: (messageList: List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    )

}