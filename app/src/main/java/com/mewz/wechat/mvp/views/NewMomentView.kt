package com.mewz.wechat.mvp.views

interface NewMomentView: BaseView {
    fun navigateToBackScreen()
    fun createNewMoment()

    fun openGallery()
}