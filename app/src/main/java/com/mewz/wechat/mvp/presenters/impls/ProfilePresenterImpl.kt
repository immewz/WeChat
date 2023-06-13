package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.*
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.ProfilePresenter
import com.mewz.wechat.mvp.views.ProfileView

class ProfilePresenterImpl: ProfilePresenter, AbstractBasePresenter<ProfileView>() {

    override var mAuthModel: AuthModel = AuthModelImpl
    override var mUserModel: UserModel = UserModelImpl
    override var mMomentModel: MomentModel = MomentModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mUserModel.getUser(
            onSuccess = { mView.showUserInformation(it) },
            onFailure = { mView.showError(it) }
        )

        mMomentModel.getMoments(
            onSuccess = { mView.showMoments(it) },
            onFailure = { mView.showError(it) }
        )
    }

    override fun onTapBookmarkButton(id: String, isBookmarked: Boolean) {
        mView.getMomentIsBookmarked(id, isBookmarked)
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

    override fun createMoment(moment: MyMomentVO) {
        mMomentModel.createMoment(moment)
    }


}