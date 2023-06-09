package com.mewz.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.delegtes.NewMomentImageViewHolderDelegate
import com.mewz.wechat.views.viewholders.MomentImageViewHolder

class NewMomentImageAdapter(
    private val delegate: NewMomentImageViewHolderDelegate
): RecyclerView.Adapter<MomentImageViewHolder>() {

    private var imageList = mutableListOf(R.drawable.button_moment_image.toString())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_moment_image, parent, false)
        return MomentImageViewHolder(view, delegate)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: MomentImageViewHolder, position: Int) {
        if (position == imageList.lastIndex){
            holder.bindData(imageList[position],-1,itemCount)
        }else {
            holder.bindData(imageList[position],position,itemCount)
        }
    }
}