package com.mewz.wechat.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.views.viewholders.ContactGroupViewHolder

class ContactGroupAdapter: RecyclerView.Adapter<ContactGroupViewHolder>() {

    private var mAlphabetList:List<Char> = listOf()
    private var mUserList:List<UserVO> = listOf()
    private var mIsGroup = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_contact_group, parent, false)
        return ContactGroupViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mAlphabetList.size
    }

    override fun onBindViewHolder(holder: ContactGroupViewHolder, position: Int) {
        holder.bindData(mAlphabetList[position], mUserList, mIsGroup)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(alphabetList: List<Char>, contactList: List<UserVO>, group: Boolean) {
        mAlphabetList = alphabetList
        mUserList = contactList
        mIsGroup = group
        notifyDataSetChanged()
    }
}