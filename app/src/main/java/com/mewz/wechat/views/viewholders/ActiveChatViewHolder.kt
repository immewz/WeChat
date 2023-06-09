package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.databinding.ViewHolderActiveChatBinding
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate

class ActiveChatViewHolder(itemView: View, private val delegate: ChatItemViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderActiveChatBinding

    init {
        binding = ViewHolderActiveChatBinding.bind(itemView)

        itemView.setOnClickListener {
            delegate.onTapChatItem()
        }
    }
}