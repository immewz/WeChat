package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.*
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.ChatPresenter
import com.mewz.wechat.mvp.views.ChatView

class ChatPresenterImpl: ChatPresenter, AbstractBasePresenter<ChatView>() {

    private var mUserModel: UserModel = UserModelImpl
    private var mAuthModel:AuthModel = AuthModelImpl
    private var mChatModel: ChatModel = ChatModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mUserModel.getUser(
            onSuccess = { mView.getUsers(it) },
            onFailure = { mView.showError(it) }
        )

        mChatModel.getGroups(
            onSuccess = {
                mView.getGroups(it)
            },
            onFailure = {
                mView.showError(it)
            }
        )
    }

    override fun onTapChatItem(userId: String) {
        mView.navigateToChatDetailScreen(userId)
    }

    override fun onTapGroupItem(groupId: Long) {
        mView.navigateToGroupChatDetailScreen(groupId)
    }

    override fun onTapCheckbox(userId: String, isChecked: Boolean) {
    }

    override fun getContacts(scannerId: String) {
        mUserModel.getContacts(
            scannerId,
            onSuccess = { mView.showContacts(it) },
            onFailure = { mView.showError(it) }
        )
    }

    override fun getUserId(): String {
        return mAuthModel.getUserId()
    }

    override fun getChatHistoryUserId(senderId: String) {
        mChatModel.getChatHistoryUserId(
            senderId,
            onSuccess = {
                mView.showUserId(it)
            },
            onFailure = {
                mView.showError(it)
            }
        )
    }

    override fun getGroupMessages(groupId: Long, onSuccess: (Int) -> Unit) {
        mChatModel.getGroupMessages(
            groupId,
            onSuccess = {
                onSuccess(it.size)
            },
            onFailure = {
                mView.showError(it)
            }
        )
    }
}