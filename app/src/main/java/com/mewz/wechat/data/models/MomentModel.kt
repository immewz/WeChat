package com.mewz.wechat.data.models

import android.graphics.Bitmap
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.network.storage.CloudFirestoreFirebaseApi

interface MomentModel {

    var mFirebaseApi: CloudFirestoreFirebaseApi

    fun createMoment(moment: MyMomentVO)
    fun uploadAndUpdateMomentImage(bitmap: Bitmap)

    fun getMomentImages()  : String
    fun getMoments(onSuccess: (moments: List<MyMomentVO>) -> Unit, onFailure: (String) -> Unit)

}