package com.mewz.wechat.mvp.views

import com.mewz.wechat.data.vos.UserVO

interface NewMomentView: BaseView {
    fun navigateToBackScreen()
    fun openGallery()

    fun showUserInformation(userList: List<UserVO>)
}