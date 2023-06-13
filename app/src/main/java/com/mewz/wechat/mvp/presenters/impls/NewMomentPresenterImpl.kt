package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.*
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.NewMomentPresenter
import com.mewz.wechat.mvp.views.NewMomentView

class NewMomentPresenterImpl: NewMomentPresenter, AbstractBasePresenter<NewMomentView>() {


    override var mAuthModel: AuthModel = AuthModelImpl
    override var mMomentModel: MomentModel = MomentModelImpl
    override var mUserModel: UserModel = UserModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mUserModel.getUser(
            onSuccess = { mView.showUserInformation(it) },
            onFailure = { mView.showError(it) }
        )
    }

    override fun onTapBackButton() {
        mView.navigateToBackScreen()
    }

    override fun onTapCreateButton(moment: MyMomentVO) {
        mMomentModel.createMoment(moment)
    }

    override fun createMomentImages(bitmap: Bitmap) {
        mMomentModel.uploadAndUpdateMomentImage(bitmap)
    }

    override fun getMomentImages(): String {
        return mMomentModel.getMomentImages()
    }

    override fun getUserId(): String {
        return mAuthModel.getUserId()
    }

    override fun onTapAddImage() {
        mView.openGallery()
    }


}