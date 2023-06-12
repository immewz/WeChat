package com.mewz.wechat.network.storage

import android.graphics.Bitmap
import com.mewz.wechat.data.vos.UserVO

interface CloudFirestoreFirebaseApi {

    fun addUser(user: UserVO)
    fun uploadAndUpdateProfileImage(bitmap: Bitmap, user: UserVO)
    fun getUser(onSuccess: (users: List<UserVO>) -> Unit, onFailure: (String) -> Unit)

}