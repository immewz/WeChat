package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.databinding.ViewHolderContactBinding
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate

class ContactViewHolder(itemView: View, private val delegate: ChatItemViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderContactBinding

    init {
        binding = ViewHolderContactBinding.bind(itemView)

        binding.cbAddGroup.visibility = View.GONE

        itemView.setOnClickListener {
            delegate.onTapChatItem()
        }
    }
}