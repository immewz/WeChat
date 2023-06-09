package com.mewz.wechat.mvp.presenters

import androidx.lifecycle.ViewModel
import com.mewz.wechat.mvp.views.BaseView

abstract class AbstractBasePresenter<T: BaseView>: BasePresenter<T>, ViewModel() {

    protected lateinit var mView: T

    override fun initPresenter(view: T) {
        mView = view
    }

}