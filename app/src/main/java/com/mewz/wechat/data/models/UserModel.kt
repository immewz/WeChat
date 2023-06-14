package com.mewz.wechat.data.models

import android.graphics.Bitmap
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.network.storage.CloudFirestoreFirebaseApi

interface UserModel {

    var mFirebaseApi: CloudFirestoreFirebaseApi

    fun addUser(user: UserVO)
    fun uploadAndUpdateProfileImage(bitmap: Bitmap, user: UserVO)
    fun getUser(onSuccess: (users: List<UserVO>) -> Unit, onFailure: (String) -> Unit)

    fun createContact(scannerId: String, qrExporterId: String, contact: UserVO)
    fun getContacts(scannerId: String, onSuccess: (users: List<UserVO>) -> Unit, onFailure: (String) -> Unit)
}