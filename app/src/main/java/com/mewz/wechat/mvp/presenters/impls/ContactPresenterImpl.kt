package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.AuthModel
import com.mewz.wechat.data.models.AuthModelImpl
import com.mewz.wechat.data.models.UserModel
import com.mewz.wechat.data.models.UserModelImpl
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.ContactPresenter
import com.mewz.wechat.mvp.views.ContactView

class ContactPresenterImpl: ContactPresenter, AbstractBasePresenter<ContactView>() {

    private var mAuthModel: AuthModel = AuthModelImpl
    private var mUserModel: UserModel = UserModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun getContacts(scannerId: String) {
        mUserModel.getContacts(
            scannerId,
            onSuccess = { mView.showContacts(it)
                        Log.d("impl","$it")},
            onFailure = { mView.showError(it) }
        )
    }

    override fun onTapGroupItem() {
    }

    override fun onTapAlphabetItem(position: Int) {}

    override fun onTapChatItem(userId: String) {
        mView.navigateToChatDetailScreen(userId)
    }

    override fun onTapCheckbox(userId: String) {}

    override fun onTapAddNewContactButton() {
        mView.navigateToNewContactScreen()
    }

    override fun onTapAddNewGroupButton() {
        mView.navigateToNewGroupScreen()
    }

    override fun getUserId(): String {
        return mAuthModel.getUserId()
    }


}