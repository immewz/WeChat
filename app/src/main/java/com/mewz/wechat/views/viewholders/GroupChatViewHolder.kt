package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mewz.wechat.R
import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.databinding.ViewHolderChatBinding
import com.mewz.wechat.delegtes.GroupItemViewHolderDelegate

class GroupChatViewHolder(itemView: View, private val delegate: GroupItemViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderChatBinding
    private var mGroupId:Long? = null

    init {
        binding = ViewHolderChatBinding.bind(itemView)
        setUpListeners()
    }

    private fun setUpListeners() {
        itemView.setOnClickListener {
            mGroupId?.let { id ->
                delegate.onTapGroupItem(id)
            }
        }
    }

    fun bindData(group: GroupVO) {
        mGroupId = group.id

        binding.tvChatName.text = group.name

        Glide.with(itemView.context)
            .load(R.drawable.mew)
            .into(binding.ivChatProfileImage)
    }


}