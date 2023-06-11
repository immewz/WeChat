package com.mewz.wechat.data.vos

data class UserVO(
    val userId: String = "",
    val userName: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val dateOfBirth:String = "",
    val gender: String = "",
    val qrCode: String = "",
    var imageUrl: String = ""
)
