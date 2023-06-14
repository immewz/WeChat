package com.mewz.wechat.mvp.views

import com.mewz.wechat.data.vos.UserVO

interface NewContactView: BaseView {
    fun getUsers(userList: List<UserVO>, qrExporterUserId:String)
}