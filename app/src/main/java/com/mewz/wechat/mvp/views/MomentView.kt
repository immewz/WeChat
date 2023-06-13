package com.mewz.wechat.mvp.views

import com.mewz.wechat.data.vos.MyMomentVO

interface MomentView: BaseView {
    fun navigateToNewMomentScreen()
    fun showMoments(moment: List<MyMomentVO>)
    fun getMomentIsBookmarked(id: String,isBookmarked:Boolean)
}