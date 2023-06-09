package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.databinding.ViewHolderGroupBinding
import com.mewz.wechat.delegtes.GroupItemViewHolderDelegate

class GroupViewHolder(itemView: View, private val delegate: GroupItemViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderGroupBinding

    init {
        binding = ViewHolderGroupBinding.bind(itemView)

        itemView.setOnClickListener {
            delegate.onTapGroupItem()
        }
    }


}