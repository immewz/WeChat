package com.mewz.wechat.mvp.presenters

import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.mvp.views.ChatView

interface ChatPresenter: BasePresenter<ChatView>, ChatItemViewHolderDelegate {
}