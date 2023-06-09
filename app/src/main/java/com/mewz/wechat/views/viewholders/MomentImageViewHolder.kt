package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.databinding.ViewHolderMomentImageBinding
import com.mewz.wechat.delegtes.NewMomentImageViewHolderDelegate

class MomentImageViewHolder(itemView: View, private val delegate: NewMomentImageViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderMomentImageBinding

    init {
        binding = ViewHolderMomentImageBinding.bind(itemView)

    }

    fun bindData(imageUrl: String, position: Int, itemCount: Int) {
        itemView.setOnClickListener {
            if (adapterPosition == itemCount - 1){
                delegate.onTapAddImage()
            }
        }

    }

}