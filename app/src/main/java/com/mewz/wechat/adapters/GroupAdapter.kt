package com.mewz.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.delegtes.GroupItemViewHolderDelegate
import com.mewz.wechat.views.viewholders.GroupViewHolder

class GroupAdapter(
    private val delegate: GroupItemViewHolderDelegate
): RecyclerView.Adapter<GroupViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_group,parent,false)
        return GroupViewHolder(view, delegate)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {

    }
}