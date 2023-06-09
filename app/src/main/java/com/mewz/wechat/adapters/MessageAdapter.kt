package com.mewz.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.views.viewholders.RecipientViewHolder
import com.mewz.wechat.views.viewholders.SenderViewHolder

class MessageAdapter: RecyclerView.Adapter<SenderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SenderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_sender, parent, false)
        return SenderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: SenderViewHolder, position: Int) {
    }
}