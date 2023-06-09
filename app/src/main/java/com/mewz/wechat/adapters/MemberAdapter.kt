package com.mewz.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.views.viewholders.MemberViewHolder

class MemberAdapter: RecyclerView.Adapter<MemberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_member, parent, false)
        return MemberViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
    }
}