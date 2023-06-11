package com.mewz.wechat.network.storage

interface RealtimeFirebaseApi {

    fun getOtp(
        onSuccess: (code: String) -> Unit,
        onFailure: (String) -> Unit
    )
}