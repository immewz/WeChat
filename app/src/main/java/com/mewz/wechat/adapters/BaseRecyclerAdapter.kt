package com.mewz.wechat.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.views.viewholders.AbstractBaseViewHolder

abstract class BaseRecyclerAdapter<T : AbstractBaseViewHolder<W>, W> : RecyclerView.Adapter<T>() {

    private var mData: ArrayList<W> = arrayListOf()

    override fun onBindViewHolder(holder: T, position: Int) {
        if (mData.size > 0) {
            holder.bindData(mData[position])
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(newData: List<W>) {
        if (newData.isEmpty()) {
            mData.clear()
        } else {
            mData = ArrayList(newData)
        }
        notifyDataSetChanged()
    }
}