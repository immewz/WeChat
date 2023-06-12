package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.AuthModel
import com.mewz.wechat.data.models.AuthModelImpl
import com.mewz.wechat.data.models.UserModel
import com.mewz.wechat.data.models.UserModelImpl
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.ProfilePresenter
import com.mewz.wechat.mvp.views.ProfileView

class ProfilePresenterImpl: ProfilePresenter, AbstractBasePresenter<ProfileView>() {

    override var mAuthModel: AuthModel = AuthModelImpl
    override var mUserModel: UserModel = UserModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mUserModel.getUser(
            onSuccess = { mView.showUserInformation(it) },
            onFailure = { mView.showError(it) }
        )
    }

    override fun onTapEditProfileButton() {
        mView.showEditProfileDialog()
    }

    override fun onTapChangeImage() {
        mView.openGallery()
    }

    override fun onTapQrCodeImage() {
        mView.showQrCode()
    }

    override fun getUserId(): String {
        return mAuthModel.getUserId()
    }

    override fun updateUserInformation(user: UserVO) {
        mUserModel.addUser(user)
    }

    override fun uploadAndUpdateProfileImage(bitmap: Bitmap, user: UserVO) {
        mUserModel.uploadAndUpdateProfileImage(bitmap, user)
    }


}