package com.mewz.wechat.network.auth

interface AuthManger {

    fun login(phoneNumber: String,email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
}