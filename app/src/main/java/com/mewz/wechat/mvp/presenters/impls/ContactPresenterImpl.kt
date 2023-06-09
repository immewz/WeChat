package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.ContactPresenter
import com.mewz.wechat.mvp.views.ContactView

class ContactPresenterImpl: ContactPresenter, AbstractBasePresenter<ContactView>() {

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }

    override fun onTapGroupItem() {
        mView.navigateToChatDetailScreen()
    }

    override fun onTapAddNewContactButton() {
        mView.navigateToNewContactScreen()
    }

    override fun onTapAddNewGroupButton() {
        mView.navigateToNewGroupScreen()
    }


}