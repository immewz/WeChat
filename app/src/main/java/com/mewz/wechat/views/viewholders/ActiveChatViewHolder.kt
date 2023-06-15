package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.ViewHolderActiveChatBinding
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate

class ActiveChatViewHolder(itemView: View, private val delegate: ChatItemViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderActiveChatBinding
    private var mUserId:String? = null

    init {
        binding = ViewHolderActiveChatBinding.bind(itemView)

        itemView.setOnClickListener {
            mUserId?.let { id ->
                delegate.onTapChatItem(id)
            }
        }
    }

    fun bindData(user: UserVO) {
        mUserId = user.userId

        Glide.with(itemView.context)
            .load(user.imageUrl)
            .into(binding.ivActiveProfileImage)
    }
}