package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mewz.wechat.databinding.ViewHolderMomentImageListBinding

class MomentImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderMomentImageListBinding

    init {
        binding = ViewHolderMomentImageListBinding.bind(itemView)
    }

    fun bindNewData(image: String) {
        Glide.with(itemView.context)
            .load(image)
            .into(binding.ivMomentPictureMoment)
    }
}