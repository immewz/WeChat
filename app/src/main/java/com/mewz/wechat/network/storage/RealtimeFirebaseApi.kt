package com.mewz.wechat.network.storage

import com.mewz.wechat.data.GroceryVO

interface RealtimeFirebaseApi {

    fun getOtp(
        onSuccess: (code: String) -> Unit,
        onFailure: (String) -> Unit
    )
}