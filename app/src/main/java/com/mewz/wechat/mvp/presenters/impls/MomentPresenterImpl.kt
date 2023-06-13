package com.mewz.wechat.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mewz.wechat.data.models.AuthModel
import com.mewz.wechat.data.models.AuthModelImpl
import com.mewz.wechat.data.models.MomentModel
import com.mewz.wechat.data.models.MomentModelImpl
import com.mewz.wechat.data.vos.MomentVO
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.mvp.presenters.AbstractBasePresenter
import com.mewz.wechat.mvp.presenters.MomentPresenter
import com.mewz.wechat.mvp.views.MomentView

class MomentPresenterImpl: MomentPresenter, AbstractBasePresenter<MomentView>() {

    private val mMomentModel: MomentModel = MomentModelImpl
    private val mAuthModel:AuthModel= AuthModelImpl
    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mMomentModel.getMoments(
            onSuccess = { mView.showMoments(it) },
            onFailure = { mView.showError(it) }
        )
    }

    override fun onTapBookmarkButton(id: String, isBookmarked: Boolean) {
        mView.getMomentIsBookmarked(id, isBookmarked)
    }

    override fun onTapNewMomentButton() {
        mView.navigateToNewMomentScreen()
    }

    override fun createMoment(moment: MyMomentVO) {
        mMomentModel.createMoment(moment)
    }

    override fun getUserId(): String {
        return mAuthModel.getUserId()
    }
}