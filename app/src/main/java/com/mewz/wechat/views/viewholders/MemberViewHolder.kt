package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.ViewHolderMemberBinding

class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private var binding: ViewHolderMemberBinding

    init {
        binding = ViewHolderMemberBinding.bind(itemView)
    }

    fun bindData(user: UserVO) {
        binding.tvProfileName.text = user.userName

        Glide.with(itemView.context)
            .load(user.imageUrl)
            .into(binding.ivContactProfileImage)
    }
}