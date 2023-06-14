package com.mewz.wechat.delegtes

interface ChatItemViewHolderDelegate {
    fun onTapChatItem(userId:String)
    fun onTapCheckbox(userId: String)
}