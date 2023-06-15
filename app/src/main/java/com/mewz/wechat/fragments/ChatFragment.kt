package com.mewz.wechat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mewz.wechat.R
import com.mewz.wechat.activities.chats.ChatDetailActivity
import com.mewz.wechat.adapters.ActiveChatAdapter
import com.mewz.wechat.adapters.ChatAdapter
import com.mewz.wechat.adapters.GroupAdapter
import com.mewz.wechat.adapters.GroupChatAdapter
import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.FragmentChatBinding
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.mvp.presenters.ChatPresenter
import com.mewz.wechat.mvp.presenters.impls.ChatPresenterImpl
import com.mewz.wechat.mvp.views.ChatView

class ChatFragment : Fragment(), ChatView {

    private lateinit var binding: FragmentChatBinding

    private lateinit var mAdapter: ActiveChatAdapter
    private lateinit var mChatAdapter: ChatAdapter
    private lateinit var mGroupAdapter: GroupChatAdapter

    private lateinit var mPresenter: ChatPresenter

    private var mUserList: List<UserVO> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_chat, container, false)

        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecyclerView()

        mPresenter.onUiReady(requireActivity(), this)

        mPresenter.getContacts(mPresenter.getUserId())
        mPresenter.getChatHistoryUserId(mPresenter.getUserId())
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[ChatPresenterImpl::class.java]
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView() {
        mAdapter = ActiveChatAdapter(mPresenter)
        binding.rvActiveChat.adapter = mAdapter
        binding.rvActiveChat.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        mChatAdapter = ChatAdapter(mPresenter)
        binding.rvChatList.adapter = mChatAdapter
        binding.rvChatList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mGroupAdapter = GroupChatAdapter(mPresenter)
        binding.rvGroupList.adapter = mGroupAdapter
        binding.rvGroupList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun navigateToChatDetailScreen(userId: String) {
        startActivity(context?.let { ChatDetailActivity.newIntent(it, userId, "") })
    }

    override fun showContacts(contactList: List<UserVO>) {
        mAdapter.setNewData(contactList)
    }

    override fun showUserId(userIdList: List<String>) {
        val chatUserList = arrayListOf<UserVO>()
        for(userId in userIdList) {
            for(user in mUserList) {
                if(userId == user.userId) {
                    chatUserList.add(user)
                    break
                }
            }
        }
        mChatAdapter.setNewData(chatUserList)
    }

    override fun getUsers(userList: List<UserVO>) {
        mUserList = userList
    }

    override fun getGroups(groupList: List<GroupVO>) {
        for(group in groupList) {

            for(userId in group.userIdList) {
                if(mPresenter.getUserId() == userId) {
                    mPresenter.getGroupMessages(
                        groupId = group.id,
                        onSuccess = {
                            if(it > 0) {
                                mGroupAdapter.setNewData(group)
                            }
                        }
                    )
                    break
                }
            }
        }
    }

    override fun navigateToGroupChatDetailScreen(groupId: Long) {
        startActivity(context?.let { ChatDetailActivity.newIntent(it, "", groupId.toString()) })
    }

    override fun showError(error: String) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
    }
}