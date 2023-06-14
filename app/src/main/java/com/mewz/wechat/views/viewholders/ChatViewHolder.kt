package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.databinding.ViewHolderChatBinding
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate

class ChatViewHolder(itemView: View, private val delegate: ChatItemViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderChatBinding

    init {
        binding = ViewHolderChatBinding.bind(itemView)

        itemView.setOnClickListener {
            delegate.onTapChatItem("1")
        }
    }
}