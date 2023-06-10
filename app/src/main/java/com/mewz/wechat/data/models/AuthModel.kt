package com.mewz.wechat.data.models

import com.mewz.wechat.network.auth.AuthManger

interface AuthModel {

    var mAuthManger: AuthManger

    fun login(phone: String,email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
}