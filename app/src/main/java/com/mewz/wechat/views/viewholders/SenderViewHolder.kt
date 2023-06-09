package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.databinding.ViewHolderSenderBinding

class SenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderSenderBinding

    init {
        binding = ViewHolderSenderBinding.bind(itemView)
    }
}