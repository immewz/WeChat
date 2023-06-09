package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.databinding.ViewHolderMemberBinding

class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private var binding: ViewHolderMemberBinding

    init {
        binding = ViewHolderMemberBinding.bind(itemView)
    }
}