package com.mewz.wechat.adapters

import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.views.viewholders.AbstractBaseViewHolder

abstract class BaseRecyclerAdapter<T : AbstractBaseViewHolder<W>, W> : RecyclerView.Adapter<T>() {

    private var mData: ArrayList<W> = arrayListOf()

    override fun onBindViewHolder(holder: T, position: Int) {
        if (mData.size > 0) {
            holder.bindData(mData[position])
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}