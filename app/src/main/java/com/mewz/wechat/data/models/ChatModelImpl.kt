package com.mewz.wechat.data.models

import android.util.Log
import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.network.storage.RealtimeDatabaseFirebaseApiImpl
import com.mewz.wechat.network.storage.RealtimeFirebaseApi

object ChatModelImpl: ChatModel {

    override var mFirebaseApi: RealtimeFirebaseApi = RealtimeDatabaseFirebaseApiImpl

    override fun getOtp(onSuccess: (code: String) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getOtp(onSuccess, onFailure)
    }

    override fun addGroup(timeStamp: Long, groupName: String, userList: List<String>) {
        mFirebaseApi.addGroup(timeStamp, groupName, userList)
    }

    override fun getGroups(
        onSuccess: (groupIdList: List<GroupVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getGroups(onSuccess, onFailure)
    }
}