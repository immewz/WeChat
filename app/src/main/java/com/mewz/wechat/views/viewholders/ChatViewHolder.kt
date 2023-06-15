package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.ViewHolderChatBinding
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate

class ChatViewHolder(itemView: View, private val delegate: ChatItemViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderChatBinding
    private var mUserId:String? = null
    init {
        binding = ViewHolderChatBinding.bind(itemView)

        itemView.setOnClickListener {
            mUserId?.let { id ->
                delegate.onTapChatItem(id)
            }
        }
    }

    fun bindData(user: UserVO) {
        mUserId = user.userId

        binding.tvChatName.text = user.userName

        Glide.with(itemView.context)
            .load(user.imageUrl)
            .into(binding.ivChatProfileImage)
    }
}