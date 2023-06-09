package com.mewz.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.views.viewholders.ActiveChatViewHolder

class ActiveChatAdapter(
    private val delegate: ChatItemViewHolderDelegate
): RecyclerView.Adapter<ActiveChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_active_chat, parent, false)
        return ActiveChatViewHolder(view, delegate)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ActiveChatViewHolder, position: Int) {

    }
}