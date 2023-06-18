package com.mewz.wechat.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.data.vos.MessageVO
import com.mewz.wechat.views.viewholders.BaseMessageViewHolder
import com.mewz.wechat.views.viewholders.ReceiveViewHolder
import com.mewz.wechat.views.viewholders.SendViewHolder

class MessageAdapter: RecyclerView.Adapter<BaseMessageViewHolder>() {

    private val VIEW_TYPE_SEND = 0
    private val VIEW_TYPE_RECIPIENT = 1

    private var mMessageList: List<MessageVO> = listOf()
    private var mUserId: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseMessageViewHolder {
        return if (viewType == VIEW_TYPE_SEND){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_send, parent, false)
            SendViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_receive, parent, false)
            ReceiveViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return mMessageList.size
    }

    override fun onBindViewHolder(holder: BaseMessageViewHolder, position: Int) {
        when (holder.javaClass) {
            SendViewHolder::class.java -> (holder as SendViewHolder).bindData(mMessageList[position])
            ReceiveViewHolder::class.java -> (holder as ReceiveViewHolder).bindData(mMessageList[position])
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = mMessageList[position]
        return if (mUserId == currentMessage.userId) {
            VIEW_TYPE_SEND
        } else {
            VIEW_TYPE_RECIPIENT
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(userId: String, messageList: List<MessageVO>) {
        mUserId = userId
        mMessageList = messageList
        notifyDataSetChanged()
    }
}