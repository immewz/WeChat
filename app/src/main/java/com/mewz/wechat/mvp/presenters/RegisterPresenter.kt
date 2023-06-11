package com.mewz.wechat.mvp.presenters

import android.graphics.Bitmap
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.mvp.presenters.BasePresenter
import com.mewz.wechat.mvp.views.RegisterView

interface RegisterPresenter: BasePresenter<RegisterView> {
    fun onTapBackButton()
    fun onTapProfileImage()
    fun onTapSignUpButton(user: UserVO, bitmap: Bitmap)

}