package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.databinding.ViewHolderGroupBinding
import com.mewz.wechat.delegtes.GroupItemViewHolderDelegate

class GroupViewHolder(itemView: View, private val delegate: GroupItemViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderGroupBinding

    init {
        binding = ViewHolderGroupBinding.bind(itemView)

    }

    fun bindData(group: GroupVO) {

        binding.tvGroupName.text = group.name

        itemView.setOnClickListener {
            delegate.onTapGroupItem(group.id)
        }

    }


}