package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.data.vos.MessageVO

abstract class BaseMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindData(message: MessageVO)
}