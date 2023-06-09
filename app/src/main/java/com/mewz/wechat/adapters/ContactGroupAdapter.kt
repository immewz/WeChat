package com.mewz.wechat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.views.viewholders.ContactGroupViewHolder

class ContactGroupAdapter: RecyclerView.Adapter<ContactGroupViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_contact_group, parent, false)
        return ContactGroupViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ContactGroupViewHolder, position: Int) {
    }
}