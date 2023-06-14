package com.mewz.wechat.network.storage

import com.mewz.wechat.data.vos.GroupVO

interface RealtimeFirebaseApi {

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