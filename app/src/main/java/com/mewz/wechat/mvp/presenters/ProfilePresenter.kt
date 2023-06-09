package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.mvp.views.ProfileView

interface ProfilePresenter: BasePresenter<ProfileView> {
    fun onTapEditProfileButton()
    fun onTapChangeImage()
    fun onTapQrCodeImage()
}