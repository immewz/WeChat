package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.mvp.views.NewContactView

interface NewContactPresenter: BasePresenter<NewContactView> {
    fun getUsers(qrExporterUserId:String)
    fun createContact(scannerId:String,qrExporterId:String,contact: UserVO)
    fun getScannerUserId(): String
}