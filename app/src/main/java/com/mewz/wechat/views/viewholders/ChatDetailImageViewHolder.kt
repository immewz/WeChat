package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mewz.wechat.databinding.ViewHolderChatDetailImageBinding

class ChatDetailImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderChatDetailImageBinding

    init {
        binding = ViewHolderChatDetailImageBinding.bind(itemView)
    }

    fun bindNewData(imageUrl:String) {
        Glide.with(itemView.context)
            .load(imageUrl)
            .into(binding.ivSendImage)
    }
}