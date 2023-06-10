package com.mewz.wechat.data.models

import android.util.Log
import com.mewz.wechat.network.storage.RealtimeDatabaseFirebaseApiImpl
import com.mewz.wechat.network.storage.RealtimeFirebaseApi

object ChatModelImpl: ChatModel {

    override var mFirebaseApi: RealtimeFirebaseApi = RealtimeDatabaseFirebaseApiImpl

    override fun getOtp(onSuccess: (code: String) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getOtp(onSuccess, onFailure)
    }
}