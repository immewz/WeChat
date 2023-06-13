package com.mewz.wechat.mvp.views

import com.mewz.wechat.data.vos.MomentVO
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.data.vos.UserVO

interface ProfileView: BaseView {
    fun showUserInformation(user: List<UserVO>)

    fun showEditProfileDialog()
    fun openGallery()
    fun showQrCode()

    fun showMoments(momentList: List<MyMomentVO>)
    fun getMomentIsBookmarked(id: String, bookmarked: Boolean)
}