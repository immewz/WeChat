package com.mewz.wechat.mvp.presenters

import android.graphics.Bitmap
import com.mewz.wechat.data.models.AuthModel
import com.mewz.wechat.data.models.MomentModel
import com.mewz.wechat.data.models.UserModel
import com.mewz.wechat.data.vos.MomentVO
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.delegtes.MomentItemViewHolderDelegate
import com.mewz.wechat.mvp.views.ProfileView

interface ProfilePresenter: BasePresenter<ProfileView>, MomentItemViewHolderDelegate {

    var mAuthModel: AuthModel
    var mUserModel: UserModel
    var mMomentModel: MomentModel

    fun onTapEditProfileButton()
    fun onTapChangeImage()
    fun onTapQrCodeImage()

    fun getUserId(): String
    fun updateUserInformation(user: UserVO)
    fun uploadAndUpdateProfileImage(bitmap: Bitmap, user: UserVO)

    fun createMoment(moment: MyMomentVO)
}