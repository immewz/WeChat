package com.mewz.wechat.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.views.viewholders.ActiveChatViewHolder

class ActiveChatAdapter(
    private val delegate: ChatItemViewHolderDelegate
): RecyclerView.Adapter<ActiveChatViewHolder>() {

    private var mContactList: List<UserVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_active_chat, parent, false)
        return ActiveChatViewHolder(view, delegate)
    }

    override fun getItemCount(): Int {
        return mContactList.count()
    }

    override fun onBindViewHolder(holder: ActiveChatViewHolder, position: Int) {
        holder.bindData(mContactList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(contactList: List<UserVO>) {
        mContactList = contactList
        notifyDataSetChanged()
    }
}