package com.mewz.wechat.data.models

import com.mewz.wechat.network.auth.AuthManger
import com.mewz.wechat.network.auth.FirebaseAuthManger

object AuthModelImpl: AuthModel {
    override var mAuthManger: AuthManger = FirebaseAuthManger

    override fun login(
        phone: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManger.login(phone, email, password, onSuccess, onFailure)
    }
}