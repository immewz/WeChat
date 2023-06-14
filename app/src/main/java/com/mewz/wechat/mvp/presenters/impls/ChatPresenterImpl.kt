package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.ChatPresenter
import com.mewz.wechat.mvp.views.ChatView

class ChatPresenterImpl: ChatPresenter, AbstractBasePresenter<ChatView>() {

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun onTapChatItem(userId: String) {
        mView.navigateToChatDetailScreen()
    }

    override fun onTapCheckbox(userId: String) {
        
    }
}