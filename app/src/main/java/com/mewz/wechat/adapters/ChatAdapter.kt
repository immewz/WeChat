package com.mewz.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.views.viewholders.ChatViewHolder

class ChatAdapter(
    private val delegate: ChatItemViewHolderDelegate
): RecyclerView.Adapter<ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_chat, parent, false)
        return ChatViewHolder(view, delegate)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
    }
}