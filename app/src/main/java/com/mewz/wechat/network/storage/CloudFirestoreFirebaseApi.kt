package com.mewz.wechat.network.storage

import android.graphics.Bitmap
import com.mewz.wechat.data.vos.MomentVO
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.data.vos.UserVO

interface CloudFirestoreFirebaseApi {

    fun addUser(user: UserVO)
    fun uploadAndUpdateProfileImage(bitmap: Bitmap, user: UserVO)
    fun getUser(onSuccess: (users: List<UserVO>) -> Unit, onFailure: (String) -> Unit)

    fun createMoment(moment: MyMomentVO)
    fun uploadAndUpdateMomentImage(bitmap: Bitmap)

    fun getMomentImages()  : String
    fun getMoments(onSuccess: (moments: List<MyMomentVO>) -> Unit, onFailure: (String) -> Unit)

    fun createContact(scannerId:String,qrExporterId:String,contact: UserVO)

    fun getContacts(scannerId:String,onSuccess: (users: List<UserVO>) -> Unit, onFailure: (String) -> Unit)

}