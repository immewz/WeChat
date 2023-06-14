package com.mewz.wechat.views.viewholders

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.activities.chats.ChatDetailActivity
import com.mewz.wechat.adapters.ContactAdapter
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.ViewHolderContactGroupBinding
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate

class ContactGroupViewHolder(itemView: View, private val delegate: ChatItemViewHolderDelegate)
    : RecyclerView.ViewHolder(itemView), ChatItemViewHolderDelegate {

    private var binding: ViewHolderContactGroupBinding

    private lateinit var mAdapter: ContactAdapter

    init {
        binding = ViewHolderContactGroupBinding.bind(itemView)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        mAdapter = ContactAdapter(delegate)
        binding.rvContactList.adapter = mAdapter
        binding.rvContactList.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onTapChatItem(userId: String) {
        itemView.context.startActivity(ChatDetailActivity.newIntent(itemView.context))
    }

    override fun onTapCheckbox(userId: String, isChecked: Boolean) {}

    fun bindData(alphabet: Char, contactList: List<UserVO>, isGroup: Boolean) {

        binding.tvContactAlphabet.text = alphabet.toString()

        val userList = arrayListOf<UserVO>()
        for(user in contactList){
            if(alphabet == user.userName[0]) {
                userList.add(user)
            }
        }

        mAdapter.setNewData(userList, isGroup)
    }

}