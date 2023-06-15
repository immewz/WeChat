package com.mewz.wechat.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.data.vos.MessageVO
import com.mewz.wechat.data.vos.PrivateMessageVO
import com.mewz.wechat.views.viewholders.BaseMessageViewHolder
import com.mewz.wechat.views.viewholders.RecipientViewHolder
import com.mewz.wechat.views.viewholders.SenderViewHolder

class MessageAdapter: RecyclerView.Adapter<BaseMessageViewHolder>() {

    private val VIEW_TYPE_SEND = 0
    private val VIEW_TYPE_RECIPIENT = 1

    private var mMessageList: List<PrivateMessageVO> = listOf()
    private var mUserId: String = ""
    private var mCurrentTime = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseMessageViewHolder {
        return if (viewType == VIEW_TYPE_SEND){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_sender, parent, false)
            SenderViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recipient, parent, false)
            RecipientViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return mMessageList.count()
    }

    override fun onBindViewHolder(holder: BaseMessageViewHolder, position: Int) {
        when (holder.javaClass) {
            SenderViewHolder::class.java -> (holder as SenderViewHolder).bindData(
                mMessageList[position]
            )
            RecipientViewHolder::class.java -> (holder as RecipientViewHolder).bindData(
                mMessageList[position]
            )
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
    fun setNewData(userId: String, messageList: List<PrivateMessageVO>) {
        mUserId = userId
        mMessageList = messageList
        notifyDataSetChanged()
    }
}