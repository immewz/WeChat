package com.mewz.wechat.mvp.presenters

import android.graphics.Bitmap
import com.mewz.wechat.data.models.AuthModel
import com.mewz.wechat.data.models.MomentModel
import com.mewz.wechat.data.models.UserModel
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.delegtes.NewMomentImageViewHolderDelegate
import com.mewz.wechat.mvp.views.NewMomentView

interface NewMomentPresenter: BasePresenter<NewMomentView>, NewMomentImageViewHolderDelegate {

    var mAuthModel: AuthModel
    var mMomentModel: MomentModel
    var mUserModel: UserModel

    fun onTapBackButton()
    fun onTapCreateButton(moment: MyMomentVO)
    fun createMomentImages(bitmap: Bitmap)

    fun getMomentImages(): String

    fun getUserId(): String
}