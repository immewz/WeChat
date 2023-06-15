package com.mewz.wechat.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.views.viewholders.ChatDetailImageViewHolder

class ChatDetailImageAdapter : RecyclerView.Adapter<ChatDetailImageViewHolder>() {

    private var mImageList:List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatDetailImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_chat_detail_image,parent,false)
        return ChatDetailImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatDetailImageViewHolder, position: Int) {
        holder.bindNewData(mImageList[position])
    }

    override fun getItemCount(): Int {
        return mImageList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(imageList:List<String>) {
        mImageList = imageList
        notifyDataSetChanged()
    }
}