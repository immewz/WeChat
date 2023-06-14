package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.AuthModel
import com.mewz.wechat.data.models.AuthModelImpl
import com.mewz.wechat.data.models.UserModel
import com.mewz.wechat.data.models.UserModelImpl
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.NewContactPresenter
import com.mewz.wechat.mvp.views.NewContactView

class NewContactPresenterImpl: NewContactPresenter, AbstractBasePresenter<NewContactView>() {

    private val mUserModel: UserModel = UserModelImpl
    private val mAuthModel:AuthModel = AuthModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun getUsers(qrExporterUserId: String) {
        mUserModel.getUser(
            onSuccess = { mView.getUsers(it, qrExporterUserId) },
            onFailure = { mView.showError(it) }
        )
    }

    override fun createContact(scannerId: String, qrExporterId: String, contact: UserVO) {
        mUserModel.createContact(scannerId, qrExporterId, contact)
    }

    override fun getScannerUserId(): String {
        return mAuthModel.getUserId()
    }


}