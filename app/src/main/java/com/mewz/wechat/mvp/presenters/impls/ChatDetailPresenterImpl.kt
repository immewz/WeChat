package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.*
import com.mewz.wechat.data.vos.MessageVO
import com.mewz.wechat.data.vos.PrivateMessageVO
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.ChatDetailPresenter
import com.mewz.wechat.mvp.views.ChatDetailView

class ChatDetailPresenterImpl: ChatDetailPresenter, AbstractBasePresenter<ChatDetailView>() {

    private val mAuthModel: AuthModel = AuthModelImpl
    private val mUserModel: UserModel = UserModelImpl
    private val mChatModel: ChatModel = ChatModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mUserModel.getUser(
            onSuccess = {
                mView.showUsers(it)
            },
            onFailure = {
                mView.showError(it)
            }
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

    override fun onTapGetImageButton() {
        mView.showGallery()
    }

    override fun getUserId(): String {
        return mAuthModel.getUserId()
    }

    override fun sendMessage(
        senderId: String,
        receiverId: String,
        timeStamp: Long,
        message: PrivateMessageVO
    ) {
        mChatModel.sendMessage(senderId, receiverId, timeStamp, message)
    }

    override fun getMessages(senderId: String, receiverId: String) {
        mChatModel.getMessages(
            senderId,
            receiverId,
            onSuccess = { messageList ->
                val sortedMessages = messageList.sortedBy { it.timeStamp }
                mView.showMessages(sortedMessages)
            },
            onFailure = {
                mView.showError(it)
            }
        )
    }

    override fun uploadAndSendImage(bitmap: Bitmap) {
        mChatModel.uploadAndSendImage(
            bitmap,
            onSuccess = {
                mView.getImageUrlForFile(it)
            },
            onFailure = {
                mView.showError(it)
            }
        )
    }

    override fun sendGroupMessage(groupId: Long, timeStamp: Long, message: PrivateMessageVO) {
        mChatModel.sendGroupMessage(groupId, timeStamp, message)
    }

    override fun getGroupMessages(groupId: Long) {
        mChatModel.getGroupMessages(
            groupId,
            onSuccess = { messageList ->
                val sortedMessages = messageList.sortedBy { it.timeStamp }
                mView.showGroupMessages(sortedMessages)
            },
            onFailure = {
                mView.showError(it)
            }
        )
    }



}