package com.mewz.wechat.data.models

import com.mewz.wechat.network.storage.RealtimeFirebaseApi

interface ChatModel {

    var mFirebaseApi: RealtimeFirebaseApi

    fun getOtp(
        onSuccess: (code: String) -> Unit,
        onFailure: (String) -> Unit
    )

}