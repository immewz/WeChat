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
import com.mewz.wechat.databinding.FragmentChatBinding
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.mvp.presenters.ChatPresenter
import com.mewz.wechat.mvp.presenters.impls.ChatPresenterImpl
import com.mewz.wechat.mvp.views.ChatView

class ChatFragment : Fragment(), ChatView {

    private lateinit var binding: FragmentChatBinding

    private lateinit var mAdapter: ActiveChatAdapter
    private lateinit var mChatAdapter: ChatAdapter

    private lateinit var mPresenter: ChatPresenter

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
    }

    override fun navigateToChatDetailScreen() {
        startActivity(context?.let { ChatDetailActivity.newIntent(it) })
    }

    override fun showError(error: String) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
    }
}