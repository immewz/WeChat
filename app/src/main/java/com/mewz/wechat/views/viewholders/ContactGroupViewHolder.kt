package com.mewz.wechat.views.viewholders

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.activities.chats.ChatDetailActivity
import com.mewz.wechat.adapters.ContactAdapter
import com.mewz.wechat.databinding.ViewHolderContactGroupBinding
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate

class ContactGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ChatItemViewHolderDelegate {

    private var binding: ViewHolderContactGroupBinding

    private lateinit var mAdapter: ContactAdapter

    init {
        binding = ViewHolderContactGroupBinding.bind(itemView)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        mAdapter = ContactAdapter(this)
        binding.rvContactList.adapter = mAdapter
        binding.rvContactList.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onTapChatItem() {
        itemView.context.startActivity(ChatDetailActivity.newIntent(itemView.context))
    }

}