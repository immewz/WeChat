package com.mewz.wechat.mvp.views

import com.mewz.wechat.data.vos.UserVO

interface ProfileView: BaseView {
    fun showUserInformation(user: List<UserVO>)

    fun showEditProfileDialog()
    fun openGallery()
    fun showQrCode()
}