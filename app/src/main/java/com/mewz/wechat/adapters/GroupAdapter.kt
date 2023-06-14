package com.mewz.wechat.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.delegtes.GroupItemViewHolderDelegate
import com.mewz.wechat.views.viewholders.GroupViewHolder

class GroupAdapter(
    private val delegate: GroupItemViewHolderDelegate
): RecyclerView.Adapter<GroupViewHolder>() {

    private var mGroupList: java.util.ArrayList<GroupVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_group,parent,false)
        return GroupViewHolder(view, delegate)
    }

    override fun getItemCount(): Int {
        return mGroupList.count()
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bindData(mGroupList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(groupList: ArrayList<GroupVO>) {
        mGroupList = groupList
        notifyDataSetChanged()
    }
}