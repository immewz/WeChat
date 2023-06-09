package com.mewz.wechat.views.viewholders

import android.view.View
import com.mewz.wechat.data.vos.MomentVO
import com.mewz.wechat.databinding.ViewHolderMomentBinding

class MomentViewHolder(itemView: View) : AbstractBaseViewHolder<MomentVO>(itemView) {

    private var binding: ViewHolderMomentBinding

    init {
        binding = ViewHolderMomentBinding.bind(itemView)
    }

    override fun bindData(data: MomentVO) {
    }

}