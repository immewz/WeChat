package com.mewz.wechat.mvp.presenters

import android.graphics.Bitmap
import com.mewz.wechat.data.models.AuthModel
import com.mewz.wechat.data.models.UserModel
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.mvp.views.ProfileView

interface ProfilePresenter: BasePresenter<ProfileView> {

    var mAuthModel: AuthModel
    var mUserModel: UserModel

    fun onTapEditProfileButton()
    fun onTapChangeImage()
    fun onTapQrCodeImage()

    fun getUserId(): String
    fun updateUserInformation(user: UserVO)
    fun uploadAndUpdateProfileImage(bitmap: Bitmap, user: UserVO)
}