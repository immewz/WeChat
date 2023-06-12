package com.mewz.wechat.data.models

import android.graphics.Bitmap
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.network.storage.CloudFirestoreFirebaseApi
import com.mewz.wechat.network.storage.CloudFirestoreFirebaseApiImpl

object UserModelImpl: UserModel {

    override var mFirebaseApi: CloudFirestoreFirebaseApi = CloudFirestoreFirebaseApiImpl

    override fun addUser(user: UserVO) {
        mFirebaseApi.addUser(user)
    }

    override fun uploadAndUpdateProfileImage(bitmap: Bitmap, user: UserVO) {
        mFirebaseApi.uploadAndUpdateProfileImage(bitmap, user)
    }

    override fun getUser(onSuccess: (users: List<UserVO>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getUser(onSuccess, onFailure)
    }
}