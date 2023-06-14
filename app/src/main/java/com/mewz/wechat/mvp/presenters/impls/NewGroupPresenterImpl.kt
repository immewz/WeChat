package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.*
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.NewGroupPresenter
import com.mewz.wechat.mvp.views.NewGroupView

class NewGroupPresenterImpl: NewGroupPresenter, AbstractBasePresenter<NewGroupView>() {

    private var mUserModel: UserModel = UserModelImpl
    private var mAuthModel: AuthModel= AuthModelImpl
    private var mChatModel: ChatModel = ChatModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun getContacts(scannerId: String) {
        mUserModel.getContacts(
            scannerId,
            onSuccess = { mView.showContacts(it) },
            onFailure = { mView.showError(it) }
        )
    }

    override fun onTapAlphabetItem(position: Int) {
    }

    override fun onTapChatItem(userId: String) {
        mView.navigateToChatDetailScreen(userId)
    }

    override fun onTapCheckbox(userId: String, isChecked: Boolean) {
        mView.addUserToGroup(userId, isChecked)
    }

    override fun getUserId(): String {
        return mAuthModel.getUserId()
    }

    override fun onTapCreateButton(timeStamp: Long, groupName: String, userList: List<String>) {
        mChatModel.addGroup(timeStamp, groupName, userList)
    }
}