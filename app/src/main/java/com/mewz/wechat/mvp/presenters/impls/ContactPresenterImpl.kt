package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.*
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.ContactPresenter
import com.mewz.wechat.mvp.views.ContactView

class ContactPresenterImpl: ContactPresenter, AbstractBasePresenter<ContactView>() {

    private var mAuthModel: AuthModel = AuthModelImpl
    private var mUserModel: UserModel = UserModelImpl
    private var mChatModel: ChatModel = ChatModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun getContacts(scannerId: String) {
        mUserModel.getContacts(
            scannerId,
            onSuccess = { mView.showContacts(it) },
            onFailure = { mView.showError(it) }
        )

        mChatModel.getGroups(
            onSuccess = { mView.getGroupList(it) },
            onFailure = { mView.showError(it) }
        )

    }


    override fun onTapAlphabetItem(position: Int) {}

    override fun onTapChatItem(userId: String) {
        mView.navigateToChatDetailScreen(userId)
    }

    override fun onTapGroupItem(groupId: String) {
        mView.navigateToChatDetailScreen(groupId)
    }

    override fun onTapCheckbox(userId: String, isChecked: Boolean) {}

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