package com.mewz.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mewz.wechat.R
import com.mewz.wechat.data.vos.MomentVO
import com.mewz.wechat.views.viewholders.MomentViewHolder

class MomentAdapter: BaseRecyclerAdapter<MomentViewHolder, MomentVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_moment, parent, false)
        return MomentViewHolder(view)
    }
}