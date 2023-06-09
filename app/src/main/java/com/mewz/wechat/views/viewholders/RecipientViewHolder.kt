package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.databinding.ViewHolderRecipientBinding

class RecipientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderRecipientBinding

    init {
        binding = ViewHolderRecipientBinding.bind(itemView)
    }
}