package com.mewz.wechat.data.models

import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.network.auth.AuthManger

interface AuthModel {

    var mAuthManger: AuthManger

    fun login(phone: String,email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun register(userName: String, email: String, phone: String, password: String,
                 dateOfBirth: String, gender: String, imageUrl: String, onSuccess: (user: UserVO) -> Unit, onFailure: (String) -> Unit)

    fun getUserId() : String
}