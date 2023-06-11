package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.AuthModel
import com.mewz.wechat.data.models.AuthModelImpl
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.RegisterPresenter
import com.mewz.wechat.mvp.views.RegisterView

class RegisterPresenterImpl: RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private var mAuthModel: AuthModel = AuthModelImpl
    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun onTapBackButton() {
        mView.navigateToBackScreen()
    }

    override fun onTapProfileImage() {
        mView.openGallery()
    }

    override fun onTapSignUpButton(user: UserVO, bitmap: Bitmap) {
        mAuthModel.register(
            userName = user.userName,
            phone= user.phone,
            email = user.email,
            password = user.password,
            dateOfBirth = user.dateOfBirth,
            gender = user.gender,
            imageUrl = user.imageUrl,
            onSuccess = {
                mView.navigateToLoginScreen()
            },
            onFailure = {
                mView.showError(it)
            }
        )

    }


}