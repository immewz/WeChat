package com.mewz.wechat.mvp.views

interface ContactView: BaseView {
    fun navigateToNewContactScreen()
    fun navigateToNewGroupScreen()
    fun navigateToChatDetailScreen()
}