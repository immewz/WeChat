package com.mewz.wechat.data.models

import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.network.storage.RealtimeFirebaseApi

interface ChatModel {

    var mFirebaseApi: RealtimeFirebaseApi

    fun getOtp(
        onSuccess: (code: String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun addGroup(timeStamp: Long, groupName: String, userList: List<String>)
    fun getGroups(
        onSuccess: (groupIdList: List<GroupVO>) -> Unit,
        onFailure: (String) -> Unit
    )

}