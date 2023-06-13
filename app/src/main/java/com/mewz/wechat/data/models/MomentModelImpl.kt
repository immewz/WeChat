package com.mewz.wechat.data.models

import android.graphics.Bitmap
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.network.storage.CloudFirestoreFirebaseApi
import com.mewz.wechat.network.storage.CloudFirestoreFirebaseApiImpl

object MomentModelImpl: MomentModel {

    override var mFirebaseApi: CloudFirestoreFirebaseApi = CloudFirestoreFirebaseApiImpl

    override fun createMoment(moment: MyMomentVO) {
        mFirebaseApi.createMoment(moment)
    }

    override fun uploadAndUpdateMomentImage(bitmap: Bitmap) {
        mFirebaseApi.uploadAndUpdateMomentImage(bitmap)
    }

    override fun getMomentImages(): String {
        return mFirebaseApi.getMomentImages()
    }

    override fun getMoments(onSuccess: (moments: List<MyMomentVO>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getMoments(onSuccess, onFailure)
    }

}